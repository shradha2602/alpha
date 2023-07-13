function error()
{
	var a = document.getElementById("empid").value
	var b = document.getElementById("empname").value
	var k = true
	if(a =="")
	{
		document.getElementById("eid").innerHTML="Employee ID should not be empty<br>"
		k = false
	}
	
	if(b =="")
	{
		document.getElementById("ename").innerHTML="Employee name should not be empty<br>"
		k = false
	}
}