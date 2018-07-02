import org.saddle.{Mat, Vec, mat, na}

// Creation
Mat(2, 2, Array(1, 2, 3, 4))
Mat(Array(1, 3), Array(2, 4))
Mat(Array(Array(1, 3), Array(2, 4)))
Mat(Vec(1, 3), Vec(2, 4))
Mat(Array(Vec(1, 3), Vec(2, 4)))

mat.ident(2)
mat.ones(2, 2)
mat.zeros(2, 2)
mat.diag(Vec(1, 2))

Mat.empty[Double]

Mat[Int](2, 2)

// Random
mat.rand(2, 2)
mat.randp(2, 2)
mat.randn(2, 2)
mat.randn2(2, 2, 3, 12)

// Operations
Mat(2, 2, Array(1, 2, 3, 4)) * Mat(2, 2, Array(4, 1, 2, 3))

Mat(2, 2, Array(1, 2, 3, 4)) dot Mat(2, 2, Array(4, 1, 2, 3))

Mat(2, 2, Array(1, 2, 3, 4)) mult Mat(2, 2, Array(4, 1, 2, 3))

Mat(2, 2, Array(1, 2, 3, 4)) dot Vec(2, 1)

Mat(2, 2, Array(1, 2, 3, 4)) * 2

Mat(2, 2, Array(1, 2, 3, 4)) + 2

Mat(2, 2, Array(1, 2, 3, 4)) << 2


Mat(2, 2, Array(1, 2, 3, 4)).T
Mat(2, 2, Array(1, 2, 3, 4)).transpose

// Properties
val m = Mat(2, 2, Array(1, 2, 3, 4))
m.numCols
m.numRows
m.isEmpty
m.isSquare

// Extract data
m.at(0, 1)
m.raw(0, 1)
m.takeRows(0)
m.withoutRows(0)
m.takeCols(0)
m.col(0)
m.row(0)
m.rows()
m.cols()

// Misc
val m1 = Mat(2, 2, Array(1, 2, na.to[Int], 4))

m1.rowsWithNA
m1.dropColsWithNA
m1.reshape(1, 4)

mat.rand(2, 2).roundTo(2)

m1.print(100, 10)