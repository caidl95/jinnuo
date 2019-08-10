	
	//加载器
	$(function(){
		$("input").focus(function(){
		    $("input").css("color","#a0b");
		 });
		  $("input").blur(function(){
		    $("input").css("color","#D6D6FF");
		  });
		var num=0;
		setInterval(function(){
			num++;
			$("#xz_tp").css({"transform":"rotate("+num+"deg)"})
		},8);
		$("#an_tp").hover(function(){
			$("#an_tp").css("background-image","url(./imgs/333.png)");
		},function(){
			 $("#an_tp").css("background-image","url(./imgs/111.png)");
		});
		// 点击关闭音乐
		$("#xm").click(function(){
			var music = document.getElementById("bgMusic");
				if(music.paused){
					music.play();
				}else{
					music.pause();	
				}
		})
		document.getElementById('sp').pause(); // 暂停视频
		// 切换轮播图
		$("#gglbt,#gly,#an_tp").click(function(){
			var state = $("#lbt").css("display")=="none"?"block":"none";
			$("#lbt").css("display",state);//隐藏轮播图
			var st = $("#an_tp").css("display")=="none"?"block":"none";
			$("#an_tp").css("display",st);
			var st = $("#sp").css("display")=="none"?"block":"none";
			$("#sp").css("display",st);//显示视频
			if(st=='none'){
				document.getElementById('sp').pause(); // 暂停视频
			}else{
				document.getElementById('sp').play(); // 播放视频
			}
			var music = document.getElementById("bgMusic");
				if(!music.paused){
					music.pause()	;
				}	
		});
		// 点击开始
		$("#djxx").click(function(){
			// 下雪
			var move=setInterval(function(){
				var w = document.body.clientWidth;
				var img = $("<img src='./imgs/snow.png'>")
				var	size= parseInt(Math.random()*21)+15;
				var left = parseInt(Math.random()*(w-size));
				img.css({"width":size+"px","height":size+"px","position":"absolute","left":left+"px",
					"z-index":"99997"});
					$("body>#tx").append(img);
				img.animate({"top":470},parseInt(Math.random()*5000)+3000).fadeOut(2000,function(){
					$(this).remove();
				});
			},100);
			$("#djgb").click(function(){
				clearInterval(move);
			})
		})
		
		//改变图片阴影
		$(".img").hover(function(){
			$(this).css({"box-shadow":"#111 0px 8px 10px","bottom":"3px","position": "relative"})
		},function(){
			$(this).css({"box-shadow":"0 0 0px #000","bottom":"0px","position": "relative"})
		});
		// 隐藏滚动条
			/*document.body.parentNode.style.overflowY = "hidden";
			$("body").parent().css("overflow-y","hidden");*/
		//点击显示
		/*$("body").click(function(){
			document.body.parentNode.style.overflowY = "auto";
			$("body").parent().css("overflow-y","auto");
		})*/
		// 定时隐藏滚动条
		/*setInterval(function(){
			document.body.parentNode.style.overflowY = "hidden";
			$("body").parent().css("overflow-y","hidden");
		},15000);*/
		//默认隐藏
		$(".gbh_gdk").css("display","none");
		//移入显示
		$("#dx").hover(function(){
			$(".gbh_gdk").css("display","block");
		},function(){
			$(".gbh_gdk").hover(function(){
				$(".gbh_gdk").css("display","block");
			},function(){
				$(".gbh_gdk").css("display","none");
			});
			$(".gbh_gdk").css("display","none");
		});
		//开启定时器获取滚动栏的高度改变固定栏
		setInterval(function(){
			var obj=$("html").scrollTop();
			parseInt(obj);
			if(obj>205){
				$(".gdh").css("background-color","rgba(255,255,255,1)");
				$("#gdh_div").css("display","inline-block");
				$(".gbh_gdk").css("background-color","rgba(255,255,255,0.8)");
				$("#gb1").css("display","block");
				$("a").css("animation","none");
			}else{
				$(".gdh").css("background-color","rgba(255,255,255,0)");
				$("#gdh_div").css("display","none");
				$(".gbh_gdk").css("background-color","rgba(255,255,255,0)");
				$("#gb1").css("display","none");
				$("a").css("animation","chan 10s linear 0s infinite normal none running");
			}
		},1);
		//点击获取采集
		$("#gdh_div_div").click(function(){
			var state = $("#gdh_div_div_cj").css("display")=="none"?"inline-block":"none";
			$("#gdh_div_div_cj").css("display",state);
		})
		//点击显示加载更多内容
		$("#jzgd").click(function(){
			$(".tb_zw_ycb").css("display","inline-block");
		})
		//点击隐藏加载更多按键
		$("#yc_jzgd").click(function(){
			$("#yc_jzgd").css("display","none");
		})
		//移入右下固定框显示内容框
		$("#gb2").hover(function(){
			$("#gb2_nr").css("display","block");
		},function(){
			$("#gb2_nr").hover(function(){
				$("#gb2_nr").css("display","block");
			},function(){
				$("#gb2_nr").css("display","none");
			});
			$("#gb2_nr").css("display","none");
		});
		
		//点击显示加号内容
		$("#gb3").click(function(){
			var state = $("#gb3_nr").css("display")=="none"?"block":"none";
			$("#gb3_nr").css("display",state);
		})
		//移入框内显现点击按钮
		$(".tb_lbt,.a_span").hover(function(){
			$(".a_span,.span_").css("display","block");
		},function(){
			$(".a_span,.span_").css("display","none");
		})
		//轮播图
		$(".tb_lbt,#left,#right").hover(function(){
			clearInterval(timer);
		},function(){
			timer = setInterval(changeImg,5000);
		});
		var arr = document.getElementsByClassName("lbt_img");
			for(var i=0;i<arr.length;i++){
				arr[i].style.left = i*1414+"px";
		}
		var timer = setInterval(changeImg,5000);
		function changeImg(){
			var moveTimer = setInterval(function(){
				var arr = document.getElementsByClassName("lbt_img");
				for(var i=0;i<arr.length;i++){
					var oldLeft = arr[i].style.left; 
					var newLeft = parseInt(oldLeft) - 2;
					if(newLeft<=-1414){
						newLeft = 1414;
						clearInterval(moveTimer);
					}
					arr[i].style.left = newLeft+"px";
				}
			},1);
		}//点击向左移动
		var kaiguan=1
		 setInterval(function(){
		 	kaiguan=1; 
		 },3800)
		$("#left").click(function(){
			if(kaiguan){ 
				changeImg();
				kaiguan = 0; 
			} 
		})
		// 向右移动
		$("#right").click(function(){
			if(kaiguan){ 
				kaiguan = 0; 
				var moveTimer = setInterval(function(){
				var arr = document.getElementsByClassName("lbt_img");
					for(var i=0;i<arr.length;i++){
						var oldLeft = arr[i].style.left;
						var newLeft = parseInt(oldLeft) + 2;
						if(newLeft>=1414){
							newLeft = -1414;
							clearInterval(moveTimer);
						}
						arr[i].style.left = newLeft+"px";
					}
				},1);
				
			} 
		})	
		// 改变颜色颜色
		$("#gbys").click(function(){
			var state = $("a").css("animation");
			if(state=="chan 10s linear 0s infinite normal none running"){
				$("a").css("animation","none");
			}else{
				$("a").css("animation","chan 10s linear 0s infinite normal none running");
			}
		})
		// 改变字体颜色
		$("#cj").click(function(){
			$(".tb_zw,.tb_zw_ycb").css("color","#a8f");
		})
		$("#hb").click(function(){
			$(".tb_zw,.tb_zw_ycb").css("color","#fa8");
		})
		$("#yh").click(function(){
			$(".tb_zw,.tb_zw_ycb").css("color","#b0f");
		})
		// 点击切换图
		
		

	})
		
		
		
	