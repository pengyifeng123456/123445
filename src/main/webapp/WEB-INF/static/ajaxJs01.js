function updateData() {
//美元符.的方式来使用Ajax请求，这里使用的是get方式，第1个参数为请求的地址
// ,和AjaxController中的 @RequestMapping(value = "/date"）相匹配(注意需要带上Web应用程序名称)，
// 第2个参数为成功获取到数据的方法， data就是返回的数据内容
    $.get("/mvc/ajax02", function (data) { //获取成功执行的方法
        window.alert('接受到异步请求数据:' + JSON.stringify(data)) //弹窗展示数据
        $("#username").text(data.username)
//这里使用了JQuery提供的选择器， 直接选择id为username的元素， 更新数据
        $("#age").text(data.age)
    })
}

function submitData() {
    $.post("/mvc/submit", // 这里使用POST方法发送请求
        {
            username: "测试",   //第二个参数是要传递的对象（这里写的是Json字符串形式），会以表单数据的方式发送
            age: 20
        }, function (data) {
            window.alert(JSON.stringify(data)) //发送成功执行的方法
        })
}

function submitData02() {
    $.ajax({ //最基本的请求方式， 需要自己设定一些参数
        type: 'POST',//设定请求方法
        url: "/mvc/submit02",//请求地址
        data: JSON.stringify({username: "测试", age: 18}), //转换为JSON字符 串进行发送
        success: function (data) {
            window.alert(JSON.stringify(data))
        },
        contentType: "application/json" //请求头Content -Type-定要设定为JSON格式
    })
}
function submitData03() {
    $.ajax({ //最基本的请求方式， 需要自己设定一些参数
        type: 'POST',//设定请求方法
        url: "/mvc/submit03",//请求地址
        data: JSON.stringify({username: "测试", age: 18}), //转换为JSON字符 串进行发送
        success: function (data) {
            window.alert(JSON.stringify(data))
        },
        contentType: "application/json" //请求头Content -Type-定要设定为JSON格式
    })
}

