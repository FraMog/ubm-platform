function empty_pin()
{
    var n=document.getElementById("cod").value;
    if (n.length < 1)
    {
        window.alert("Inserire PIN!");
        return false;
    }
}