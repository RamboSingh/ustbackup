
(function() {
		 var getStyle;
		 var prev; 
		 var sPropVals="";	
		 var properties;
		 document.body.onmouseover = MouseoverEventhandler;
		 document.body.onmouseout = MouseoutEventhandler;
		 document.body.onmousedown = Elementclickhandler;  
		
		 document.addEventListener('copy', function(e){
			e.clipboardData.setData('text/plain',sPropVals);
			e.preventDefault(); // default behaviour is to copy any selected text
		 });	
	
		 function MouseoutEventhandler(e) { 
			var from = e.relatedTarget || e.toElement;
			if (!from || from.nodeName == "HTML") {
					prev.setAttribute('style',getStyle);
			}
		 } 
		 function MouseoverEventhandler(event) { 
			  if (event.target === document.body || (prev && prev === event.target)) { 
				return; 
			  } 
			  if (prev) {   
				prev.setAttribute('style', getStyle);
			  }
			  if (event.target!=null) { 
				prev = event.target; 
				getStyle=prev.getAttribute('style'); 
				prev.setAttribute('style', 'border: 2px solid red;');  		
			 } 
		 } 

		 function Elementclickhandler(key) { 
	
				
			  if (navigator.appName == "Netscape"  && key.which == 1){ 
				properties=key.target.attributes;
				sPropVals="xpath: "+getXpath(key.target)+"  \n";
				
                for (i=0;i<properties.length;i++)
                	sPropVals=sPropVals + properties[i].name + " : "+ properties[i].value+"\n";
                document.execCommand('copy');
				alert(sPropVals);
				//tempAlert(key.target,sPropVals,3000);
				return false;  
			  } 
		 } 
/*TEMP
	function tempAlert(parent,msg,duration)
		{
		 var el = document.createElement("div");
		 //el.setAttribute("style","position:absolute;top:40%;left:20%;background-color:WHITE");
		//el.setAttribute("style","position:absolute;	background-color:#eeeefe;font size:4px; 	border: 1px solid #aaaaca;	font-size: smaller;	padding:4px;	width: 16o0px;	box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.1);	-moz-box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.1);-webkit-box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.1);");
		el.setAttribute("style","none");
		el.setAttribute("style","position:absolute;	font-family: 'Times New Roman', Times, serif;font-size:20px;font-style:normal;font-weight: normal;border: 1px solid #aaaaca;color:black;");
		 el.innerHTML = msg;
		 setTimeout(function(){
		  el.parentNode.removeChild(el);
		 },duration);
		//document.body.appendChild(el);
			parent.appendChild(el);
		}
*/		 
//For getting Xpath 
		function getXpath(currentNode){
			var path=[];
			while(currentNode){
				var pe=getNode(currentNode);
				if(pe){
					path.push(pe);
					if(pe.indexOf('@id')!=-1)break;
				}
				currentNode=currentNode.parentNode;
			}
			var xpath="/"+path.reverse().join('/'); 
			return xpath;
		}
         function getNode(node){
			 var nodeExpr=node.tagName;
			 if(!nodeExpr)return null;
			 if(node.id!=''){
				 nodeExpr+="[@id='"+node.id+"']";
				 return "/"+nodeExpr;
			 }
			 var rank=1;
			 var ps=node.previousSibling;
			 while(ps){
				 if(ps.tagName==node.tagName){
					 rank++;
			 	}
				ps=ps.previousSibling;
			 }
			 if(rank>1){
				nodeExpr+='['+rank+']';
			}else{
				var ns=node.nextSibling;
				while(ns){
					if(ns.tagName==node.tagName){
						nodeExpr+='[1]';
						break;
					}
				ns=ns.nextSibling;
				}
			}
			 return nodeExpr;
		}
})(); 

