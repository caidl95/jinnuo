function setCookie(c_name,value,expiredays)
{
    var exdate = new Date();
    exdate.setDate(exdate.getDate()+expiredays);
    //对value值进行编码
    document.cookie = c_name + "=" + escape(value)+
        ((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
}
function getCookie(c_name)
{
    if(document.cookie.length>0){
        c_start = document.cookie.indexOf(c_name + "=");
        if(c_start!=-1){
            c_start = c_start + c_name.length+1;
            c_end = document.cookie.indexOf(";",c_start);
            if(c_end==-1)
                c_end = document.cookie.length;
            //解码
            return unescape(document.cookie.substring(c_start,c_end));
        }
    }
    return "not log in";
}