import org.saddle.stats.PctMethod
import org.saddle.stats.RankTie.Avg
import org.saddle.{*, Vec, na, vec, scalar}

// Vector creation
Vec(1, 2, 3)
Vec(1 to 3: _*)
Vec(Array(1, 2, 3))
Vec(Seq(1, 2, 3))
Vec(Seq(1, 2, 3): _*)
Vec.empty[Double]

// Creation using factories
vec.ones(5)
vec.zeros(5)

// Creation using randomized factories
vec.rand(1000)
vec.randp(1000)
vec.randi(1000)
vec.randpi(1000) % 10
vec.randn(1000)
vec.randn2(100, 2, 15)

// Arithmetic
Vec(1, 2, 3) + Vec(4, 5, 6)
Vec(1, 2, 3) * Vec(4, 5, 6)
Vec(1, 2, 3) dot Vec(4, 5, 6)
Vec(1, 2, 3) outer Vec(4, 5, 6)
Vec(1, 2, 3) ** Vec(4, 5, 6)
Vec(1, 2, 3) << 2
Vec(1, 2, 3) & 0x1
Vec(1, 2, 3) + 2

// Slicing
val v = vec.rand(10)

v.at(2)
v.raw(2)

v(2, 4, 8)
v(2 -> 8)
v(* -> 3)
v(8 -> *)
v.slice(0, 3)
v.slice(0, 8, 2)


// Statistics
val v1 = Vec(1, 2, 3)
v1.sum
v1.prod
v1.mean
v1.median
v1.max
v1.min
v1.stdev
v1.variance
v1.skew
v1.kurt
v1.geomean
v1.count
v1.countif(_ > 0)
v1.logsum
v1.argmin
v1.argmax
v1.percentile(0.3, method = PctMethod.NIST)
v1.demeaned
v1.rank(tie = Avg, ascending = true)

v.rollingSum(5)
v.rollingMean(5)
v.rollingMedian(5)
v.rollingCount(5)

v.rolling(5, _.stdev)

// Advanced

v filter (_ > 0.5)
v where v > 0.5
v take v.find(_ > 0.5)

v.filterFoldLeft(_ > 0.5)(0d) { case (acc, d) => acc + d}
v shift 1

v.reversed
v.mapValues(_ + 1)
v.foldLeft(0d) { case (acc, d) => acc + 1.0 / d }
v.scanLeft(0d) { case (acc, d) => acc + 1.0 / d }
v without v.find(_ < 0.5)
v findOne(_ < 0.5)
v head 2
v tail 2
v(0 -> 2) mask Vec(true, false, true)
v concat v1


// NaNs
val v2 = Vec(1, na.to[Int], 2)

v2 sum

v2 median

v2 prod

v2 dropNA

v2 at 1

v2 raw 1

v2.fillNA(x => x)

val d = scalar.Scalar(1.0)

// Unboxing
v.toSeq
v.contents