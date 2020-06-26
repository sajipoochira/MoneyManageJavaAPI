<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script src="PapaParse-5.0.2/papaparse.js"></script>
<script src="jquery-csv-master/src/jquery.csv.js"></script>
</head>
<body>
    <button id="Print">

        
        Click me

    </button>
    <div class ="container">
        <div class="col-md-12">
        <table id = "expenceTable" class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Date</th>
                    <th scope="col">Account</th>
                    <th scope="col">Category</th>
                    <th scope="col">Amount</th>
                    <th scope="col">Description</th>

                </tr>

            </thead>
            <tbody id = "dataTable">


            </tbody>

        </table>
    </div>
    </div>
<script>

document.getElementById("Print").addEventListener("click",ReadCsvfile)
function ReadCsvfile() {
    $.ajax({
        type: "GET",
        url: "Monefy.Data.20-05-2020.csv",
        dataType: "text",
        success: function(data) {processData(data);}
     });
}

function processData(allText) {

  
    var data = $.csv.toObjects(allText);
    var XHR =new XMLHttpRequest();

   
  
    var output = csvJSON(allText)

  var JsonArray = JSON.parse(output)

 


JsonArray.forEach(myFunction);
var htmlElement = ""
  function myFunction(item, index) {

    if(item.amount <0){
        
        var dateformated = (item.date).split("/").reverse().join("-");
       htmlElement+=`<tr><td>${dateformated}</td><td>${(item.account).toString()}</td><td>${(item.category).toString()}</td><td>${Math.abs(item.amount)}</td><td>${(item.description).toString()}</td></tr>`
  var expence = {
    
        "account": (item.account).toString(),
        "date": dateformated,
        "expence_Amount": Math.abs(item.amount),
        "expence_Catogory": (item.category).toString(),
        "expence_Description": (item.description).toString()
}
 /* 
var expence = {
    
    "account": "CreditCard",
        "date": "2020-05-16",
        "expence_Amount": 298,
        "expence_Catogory": "Food",
        "expence_Description": "Grocery Grand"
}
*/
XHR.open("POST", "http://localhost:8080/MoneyManage/api/expence");
XHR.setRequestHeader("Content-Type" , "application/json")
var jsonexpence = JSON.stringify(expence);
XHR.send(jsonexpence)
console.log(XHR.responseText)
}
document.getElementById("dataTable").innerHTML = htmlElement
}
  

  // 

function CreateinputJson(item, index){





return expence

}
}
function csvJSON(csv){

var lines=csv.split("\n");

var result = [];

// NOTE: If your columns contain commas in their values, you'll need
// to deal with those before doing the next step 
// (you might convert them to &&& or something, then covert them back later)
// jsfiddle showing the issue https://jsfiddle.net/
var headers=lines[0].split(",");

for(var i=1;i<lines.length;i++){

    var obj = {};
    var currentline=lines[i].split(",");

    for(var j=0;j<headers.length;j++){
        obj[headers[j]] = currentline[j];
    }

    result.push(obj);

}

//return result; //JavaScript object
return JSON.stringify(result); //JSON
}
/*
var XHR =new XMLHttpRequest();
XHR.open("POST", "http://localhost:8080/MoneyManage/api/expence");
XHR.setRequestHeader("Content-Type" , "application/json")
var expence = {
    
    "account": "CreditCard",
        "date": "2020-05-16",
        "expence_Amount": 298,
        "expence_Catogory": "Food",
        "expence_Description": "Grocery Grand"
}

var jsonexpence = JSON.stringify(expence);

console.log(jsonexpence)
*/


</script>

</body>
</html>