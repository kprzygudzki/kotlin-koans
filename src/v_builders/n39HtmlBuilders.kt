package v_builders

import util.TODO
import util.doc39
import v_builders.data.Product
import v_builders.data.getProducts
import v_builders.htmlLibrary.*

fun getTitleColor() = "#b9c9fe"
fun getCellColor(row: Int, column: Int) = if ((row + column) %2 == 0) "#dce4ff" else "#eff2ff"

fun todoTask39(): Nothing = TODO(
    """
        Task 39.
        1) Fill the table with the proper values from products.
        2) Color the table like a chess board (using getTitleColor() and getCellColor() functions above).
        Pass a color as an argument to functions 'tr', 'td'.
        You can call the 'main' function in the 'htmlDemo.kt' to see the rendered table.
    """,
    documentation = doc39()
)

data class ColumnDefinition(val header: String, val mapper: (Product) -> Any)

fun renderProductTable(): String {
    val columns = listOf(
            ColumnDefinition("Product", { product -> product.description }),
            ColumnDefinition("Price", { product -> product.price }),
            ColumnDefinition("Popularity", { product -> product.popularity }))

    return html {
        table {
            tr {
                columns.map { column -> column.header }.map {
                    td(color = getTitleColor()) {
                        text(it)
                    }
                }
            }
            val products = getProducts()
            products.mapIndexed { rowIndex, product ->
                tr {
                    columns.map { column -> column.mapper }.mapIndexed { colIndex, colFunction ->
                        td(color = getCellColor(rowIndex, colIndex)) {
                            text(colFunction(product))
                        }
                    }
                }
            }
        }
    }.toString()
}
