var express=require('express');
var mysql=require('mysql');

var app=express();
var con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "",
    database:"mytoken"
});



var qu;
var q="INSERT INTO doctor( doc_name, doc_speciality, doc_degree, experience, location,available,curr_token,expected,max_token,fees) VALUES ('Dr. laky','ENT','MBBS','5','delhi',1,20,35,100,500)";
var s="Select * from doctor ";
con.connect(function(err) {
    if (err) throw err;
    console.log("list!");

    con.query(s, function (err, result) {
        if (err) throw err;
       qu=result
    });
});



var server=app.listen(3000);
app.post('/list',(req,res)=>{
res.status(200).json({
       qu
});
});