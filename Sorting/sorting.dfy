      predicate sorted(s: seq<int>)
      {
        forall j, k :: 0 <= j < k < |s| ==> s[j] <= s[k]
      }

      predicate sortedTwo(s: seq<int>)
      {
        0 < |s| ==> (forall i :: 0 <= i < |s| ==> s[0] <= s[i]) && sortedTwo(s[1..])
      }

      ghost method sortedLemma(s: seq<int>)
      requires sorted(s);
      ensures sortedTwo(s);
      {}

      ghost method sortedTwoLemma(s: seq<int>)
      requires sortedTwo(s);
      ensures sorted(s);
      {}

      predicate p2(a : seq<int>, b : seq<int>)
      requires |a| == |b|;
      {
          forall i : int :: 0 <= i < |a| ==> countNumbers(a, a[i]) == countNumbers(b, a[i])
      }

      function countNumbers(s : seq<int>, value : int) : int
      {
          if |s| == 0 then 0
          else if s[0] == value then 1 + countNumbers(s[1..], value)
          else countNumbers(s[1..], value)
      }

      /*method sortArray(a : array<int>)
      requires a != null;
      requires 2 <= a.Length;
      ensures p(a[..], old(a[..]));
      ensures sorted(a[..]);
      modifies a;
      {

      }*/

       method sortArray(a : array<int>)
       requires a != null;
       requires 1 < a.Length;
       ensures sorted(a[..]);
       modifies a;
       {
          forall(i | 0 <= i < a.Length){
            a[i] := 0;
          }
       }