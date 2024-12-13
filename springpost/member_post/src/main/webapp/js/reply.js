// const replyService =  {};
const replyService = (function() { 
    const url = "/member_post/reply";
    function write(reply, callback){
        console.log(reply);
        // reply.
        // JSON.stringify(arg) :: obj -> json String 으로 바꿔줌.
        // JSON.parse(arg) :: json -> obj 
        const data = JSON.stringify(reply);
        $.post({
            url, 
            data
        }).done(function(data){
            console.log(data);
			 if(callback){
	            callback();
	    	}
        })
	}
    function list(pno, cri, callback){
		let reformedUrl = url + "/list/" + pno;
		if(cri && cri.lastRno){
			reformedUrl += "/" + cri.lastRno;
			if(cri.amount){
				reformedUrl += "/" + cri.amount;
			}
		}
        // 비동기 : ajax
        $.getJSON(reformedUrl).done(function(data){
            if(callback){
                callback(data);
            }
        });

        // $.ajax({
        //     url : url + "/list/" + pno,
        //     method : 'GET',
        //     dataType : 'JSON',
        //     success : function(data){
        //         console.log(data)
        //     }
        // })
    }
    function view(rno, callback){
        $.getJSON(url + "/" + rno).done(function(data){
            if(callback)
                callback(data);
        }).done(function(data){
            if(callback)
                callback(data);
        })
    }

    function modify(reply, callback){
        const data = JSON.stringify(reply);
        $.ajax(url,{
            method : "put"
        }).done(function(data){
            if(callback)
                callback(data);
        })
    }

    function remove(rno, callback){     
        $.ajax(url + "/" + rno, {
            method : 'delete'
        }).done(function(data){
            if(callback)
                callback(data);
        });
    }

    // 글삭제 했을 때만 전체삭제 가능하게 할 것이라서
    // 여기서는 

    return {write, list, view, modify, remove}    
})();
// 함수 내부는 결국 내부로부터 보호받는 공간. 
// url 같은 경우는 약간의 변화만 생기고 큰 변화가 없기 때문에 