(: Licensed to the Apache Software Foundation (ASF) under one
   or more contributor license agreements.  See the NOTICE file
   distributed with this work for additional information
   regarding copyright ownership.  The ASF licenses this file
   to you under the Apache License, Version 2.0 (the
   "License"); you may not use this file except in compliance
   with the License.  You may obtain a copy of the License at
   
     http://www.apache.org/licenses/LICENSE-2.0
   
   Unless required by applicable law or agreed to in writing,
   software distributed under the License is distributed on an
   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
   KIND, either express or implied.  See the License for the
   specific language governing permissions and limitations
   under the License. :)
   
(: Search Lucene Index :)
for $r in collection-from-index("src/test/resources/TestSources/ghcnd/half_1|src/test/resources/TestSources/ghcnd/half_2", "/dataCollection/data")/data
let $datetime := xs:dateTime(fn:data($r/date))
where $r/station eq "GHCND:AS000000003" 
    and fn:year-from-dateTime($datetime) ge 2000
    and fn:month-from-dateTime($datetime) eq 3 
    and fn:day-from-dateTime($datetime) eq 3
return $r