package scalaworld

import scalaz.Functor

package object concepts {

  case class Fix[F[_]](unfix: F[Fix[F]])

  type Algebra[F[_], A] = F[A] => A
  type Coalgebra[F[_], A] = A => F[A]

  //equivalent of fold
  def cata[F[_], A](fix: Fix[F])(algebra: Algebra[F, A])(implicit F: Functor[F]): A = ???

  //equivalent of unfold
  def ana[F[_], A](a: A)(coalgebra: Coalgebra[F, A])(implicit F: Functor[F]): Fix[F] = ???

  //unfold and then fold
  def hylo[F[_], A](a: A)(coalgebra: Coalgebra[F, A], algebra: Algebra[F, A]): Fix[F] = ???
}
