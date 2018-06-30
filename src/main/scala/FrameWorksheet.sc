import org.saddle.index.{LeftJoin, OuterJoin}
import org.saddle.{Frame, Index, Panel, Series, Vec, mat}

// Instantiation
val f: Frame[Int, Int, Double] = mat.rand(2, 2)

val v = Vec(1, 2)
val u = Vec(3, 4)
val s = Series("a" -> 1, "b" -> 2)
val t = Series("b" -> 3, "c" -> 4)

Frame(v, u)
Frame("x" -> v, "y" -> u)
Frame(s, t)
Frame("x" -> s, "y" -> t)
Frame(Seq(s, t), Index("x", "y"))
Frame(Seq(v, u), Index(0, 1), Index("x", "y"))
Frame(Seq(v, u), Index("a", "b"))

// Heterogeneous frame
val p = Panel(Vec(1, 2, 3), Vec("a", "b", "c"))

p.colType[Int]
p.colType[Int, String]

// Generate empty row or column
f.emptyCol
f.emptyRow


val f1 = Frame("x" -> s, "y" -> t)
f1.setRowIndex(Index(10, 20))
f1.setColIndex(Index("p", "q"))
f1.resetRowIndex
f1.resetColIndex

// Transformations

f1.mapRowIndex(rx => rx + 1)
f1.mapColIndex(cx => cx + 2)

// Extract data
f1.rowAt(2)
f1.rowAt(1, 2)
f1.rowAt(1 -> 2)

f1.colAt(1)
f1.colAt(0, 1)
f1.colAt(0 -> 1)

f1.at(1, 1)
f1.at(Array(1, 2), 0)
f1.at(0 -> 1, 1)
f1.at(0 -> 1, 0 -> 1)

// Slicing
f1.colSlice(0, 1)
f1.rowSlice(0, 3, 2)

f1.row("a")
f1.col("x")
f1.row("a", "c")
f1.row("a" -> "b")
f1.row(Vec("a", "c"))

f1.rowSliceBy("a", "b", inclusive = false)
f1.colSliceBy("x", "x", inclusive = true)

f1("a", "x")
f1("a" -> "b", "x")
f1(Vec("a", "c"), "x")

// Splitting
f1.colSplitAt(1)
f1.colSplitBy("y")

f1.rowSplitAt(1)
f1.rowSplitBy("b")

f1.head(2)
f1.tail(2)
f1.headCol(1)
f1.tailCol(1)
f1.first("b")
f1.last("b")
f1.firstCol("x")
f1.lastCol("x")

f1.filter(s => s.mean > 2.0)
f1.filterIx(x => x == "x")
f1.where(Vec(false, true))  // Extracts second column

// NaNs

f1.dropNA
f1.rdropNA

// Operations
f1 + 1
f1 * f1
val g = Frame("y" -> Series("b" -> 5, "d" -> 10))
f1 + g

f1.joinMap(g, rhow = LeftJoin, chow = LeftJoin) { case (x, y) => x + y }

val (fNew, gNew) = f1.align(g, rhow = LeftJoin, chow = OuterJoin)


// Sorting
f1.sortedRIx
f1.sortedCIx
f1.sortedRows(0, 1)
f1.sortedCols(1, 0)
f1.sortedRowsBy(r => r.at(0))
f1.sortedColsBy(c => c.at(0))

// Mapping
f1.mapValues(t => t + 1)
f1.mapVec(v => v.demeaned)
f1.reduce(s => s.mean)
f1.transform(s => s.reversed)

// Masking
f1.mask(_ > 2)
f1.mask(Vec(false, true, true))

f1.mask(Vec(true, false, false)).rsqueeze
f1.rmask(Vec(false, true)).squeeze

// Group by
f1.groupBy(_ == "a").combine(_.count)
f1.groupBy(_ == "a").transform(_.demeaned)

// Join
f1.join(g, how = LeftJoin)
f1.join(s, how = LeftJoin)
f1.joinS(s, how = LeftJoin)




