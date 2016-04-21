Date.prototype.format = function (fmt) {
    var year = this.getFullYear();
    var month = this.getMonth() + 1;
    var date = this.getDate();
    var hour = this.getHours();
    var minute = this.getMinutes();
    var second = this.getSeconds();

    fmt = fmt.replace("yyyy", year);
    fmt = fmt.replace("yy", year % 100);
    fmt = fmt.replace("MM", fix(month));
    fmt = fmt.replace("dd", fix(this.getDate()));
    fmt = fmt.replace("hh", fix(this.getHours()));
    fmt = fmt.replace("mm", fix(this.getMinutes()));
    fmt = fmt.replace("ss", fix(this.getSeconds()));
    return fmt;
    function fix(n) {
        return n < 10 ? "0" + n : n;
    }
}
function getDate(mill, format) {
    var date = new Date();
    date.setTime(mill);
    return  date.format(format);
}

/**
 * 毫秒转换为 hh:mm:ss格式
 * @param {type} msec
 * @param {type} useString
 * @returns {String}
 */
function getTime(msec) {

    useString = true;
    // convert milliseconds to hh:mm:ss, return as object literal or string

    var nSec = Math.floor(msec / 1000),
            hh = Math.floor(nSec / 3600),
            min = Math.floor(nSec / 60) - Math.floor(hh * 60),
            sec = Math.floor(nSec - (hh * 3600) - (min * 60));

    // if (min === 0 && sec === 0) return null; // return 0:00 as null

    return (useString ? ((hh ? hh + ':' : '') + (hh && min < 10 ? '0' + min : min) + ':' + (sec < 10 ? '0' + sec : sec)) : {'min': min, 'sec': sec});

}
/**
 * 把时间转换为毫秒
 * @param {type} time
 * @returns {undefined}
 */
function toMsec(time){

    var timeArr = time.split(":");
    var times  = 1;
    var sec = 0;
    for(var i = timeArr.length-1; i >=0 ;i--){

        var t = new Number(timeArr[i]);

        sec+=t*times;
        times*=60;
    }
    
    return sec*1000;
}