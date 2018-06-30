import org.saddle.index.{InnerJoin, LeftJoin, OuterJoin, RightJoin}
import org.saddle.stats.SeriesRollingStats
import org.saddle.{*, Index, Series, Vec}
// Creation
Series(Vec(32, 12, 9))
Series("a" -> 1, "b" -> 2, "c" -> 3)
Series(List("a" -> 1, "b" -> 2, "c" -> 3): _*)
Series(Vec(1, 2, 3), Index("a", "b", "c"))
Series.empty[String, Int]
Series(Vec(1, 2, 3), Index("c", "b", "a"))
Series(Vec(1, 2, 3, 4), Index("c", "b", "a", "b"))

// Extract data
val q = Series(Vec(1, 3, 2, 4), Index("c", "b", "a", "b"))

q.values
q.index

q.at(2)
q.at(2, 3, 1)

q.keyAt(2)
q.keyAt(2, 3, 1)

q.sortedIx
q.sorted

q("b")

q("a", "b")
q("b", "a")

q.first
q.last

q.firstKey
q.lastKey

q.reindex(Index("a", "c", "d"))
q("a", "d")

q.reindex("a", "c", "d")
q("a", "d")

// q.reindex("a", "b") // Troublesome because b is ambiguous

q.resetIndex

q.setIndex(Index("w", "x", "y", "z"))

val s = q.sortedIx
s.sliceBy("b", "c")

s.sliceBy("b" -> "c")
s.sliceBy(* -> "b")

q.slice(0, 2)

q.head(2)
q.tail(2)

// Computing
val q1 = Series(Vec(1, 2, 3, 4), Index("c", "b", "a", "b"))

q1.mapValues(_ + 1)
q1.mapIndex(_ + 1)
q1.mapIndex(_ + "x")
q1.shift(1)
q1.filter(_ > 2)
q1.filterIx(_ != "b")
q1.filterAt(loc => loc != 1 && loc != 3)
q1.find(_ == 2)
q1.findKey(x => x == 2 || x == 3)
q1.findOneKey(x => x == 2 || x == 3)
q1.minKey
q1.contains("a")
q1.scanLeft(0) { case (acc, v) => acc + v }
q1.reversed

val m = q1.mask(q1.values > 2)
m.hasNA
m.dropNA
m.pad

q1.rolling(2, _.minKey)
q1.splitAt(2)
q1.sortedIx.splitBy("b")

// Converting
q1.toVec
q1.toSeq

// Grouping
q1.groupBy.combine(_.sum)
q1.groupBy.transform(s => s - s.mean)

// Operations
var a = Series(Vec(1, 4, 2, 3), Index("a", "b", "c", "d"))
var b = Series(Vec(5, 2, 1, 8, 7), Index("b", "c", "d", "e", "f"))

a + b

a = Series(Vec(1, 4, 2), Index("a", "b", "b"))
b = Series(Vec(5, 2, 1), Index("b", "b", "d"))

a + b

a.join(b, how = LeftJoin)
a.join(b, how = RightJoin)
a.join(b, how = InnerJoin)
a.join(b, how = OuterJoin)

val t = Series(Vec(1, 2, 3, 4), Index((1, 1), (1, 2), (2, 1), (2, 2)))

val f = t.pivot

f.melt