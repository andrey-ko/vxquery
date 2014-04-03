(: XQuery Aggregate Query :)
(: Find the highest recorded temperature (TMAX) in Celsius.                   :)
    let $collection := "ghcnd"
    for $r in collection($collection)/dataCollection/data
    where $r/dataType eq "TMAX"
    return $r/value