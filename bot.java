Bots

---------------------------------


Group PM bot:



var Arri = []
var i = -1
 
function NextPage(){
       __doPostBack('ctl00$cphRoblox$rbxGroupRoleSetMembersPane$dlUsers_Footer$ctl02$ctl00','')
}
 
function Check(){
   $('#ctl00_cphRoblox_rbxGroupRoleSetMembersPane_GroupMembersUpdatePanel > > > > > .Name >').each(function(){
 var g = $(this).attr('href')
 var f = $(this).attr('href').replace('../User.aspx?ID=','')
 console.log(f)
 Arri.push(f)
})
}
function sendMsg()
{
        $.post("http://www.roblox.com/messages/send",{
                subject                 :               "Hey there, send me a trade!",
                body                    :               "Send on all of my items, over 25K in limiteds! I am upgrading and downgrading, but if I am downgrading you must OP. The only thing I am not trading at the moment are my Breezekreig Adventurers.",
                recipientid             :               Arri[i],
                cacheBuster             :               new Date().getTime()
        }).complete(function(){
                console.log('Sent message to ' + username + '.')
        })
}
 var sendd = setInterval(function(){
       i++;
       sendMsg()
 },10000)
setInterval(function(){
        Arri.length = 0
        i = 0
        NextPage()
             setTimeout(function(){
         Check()
         },2000)
},103000)
 
Check()




---------------------------------





Forum Bot #1



var RandomPosts = ["lolkool","uhhh sure!","my name is roooobot"]
var ForumId = 38
function Post(){
        $.get("http://www.roblox.com/Forum/ShowForum.aspx?ForumID=" + ForumId,function(Data){
                var Link = $(Data).find(".linkSmallBold")[4].href
                Link = "http://www.roblox.com/Forum/AddPost.aspx?mode=flat&PostID=" + Link.replace("http://www.roblox.com/Forum/ShowPost.aspx?PostID=","")
                $.get(Link,function(Data){
                        var VS = Data.match(/id="__VIEWSTATE" value="(.+)"/)[1]
                        var EV = Data.match(/id="__EVENTVALIDATION" value="(.+)"/)[1]
                        $.post(Link,{
                                "__VIEWSTATE" : VS,
                                "__EVENTVALIDATION" : EV,
                                "ctl00$cphRoblox$Createeditpost1$PostForm$PostSubject" : "Re: -",
                                "ctl00$cphRoblox$Createeditpost1$PostForm$PostBody" : RandomPosts[Math.floor((Math.random() * RandomPosts.length) + 1)],
                                "ctl00$cphRoblox$Createeditpost1$PostForm$PostButton" : "Post"
                        })
                })
        })
}
Post()
var Interval = setInterval(function(){
        Post()
},40000)



---------------------------------



Forum Bot #2:



var ThreadId = prompt("What thread Id would you like to bump?")
var BumpTimes = prompt("How many times would you like to bump this thread?")
//
var OnBump = 0
var Link = "http://www.roblox.com/Forum/AddPost.aspx?mode=flat&PostID=" + ThreadId
function Post(){
        $.get(Link,function(Data){
                var VS = Data.match(/id="__VIEWSTATE" value="(.+)"/)[1]
                var EV = Data.match(/id="__EVENTVALIDATION" value="(.+)"/)[1]
                $.post(Link,{
                        "__VIEWSTATE" : VS,
                        "__EVENTVALIDATION" : EV,
                        "ctl00$cphRoblox$Createeditpost1$PostForm$PostSubject" : "Re: -",
                        "ctl00$cphRoblox$Createeditpost1$PostForm$PostBody" : OnBump,
                        "ctl00$cphRoblox$Createeditpost1$PostForm$PostButton" : "Post"
                })
        })
}
Post()
var Interval = setInterval(function(){
        if (OnBump == BumpTimes){
                clearInterval(Interval)
        }
        Post()
        OnBump++
},40000)





---------------------------------




Comment Bot:



var Comment = "Send trades on over 25K in limiteds! Accepting and countering all trades!"
//
var PageOn = 1
var PageLimit = 96
function Go(){
        console.log("On page " + PageOn)
        var BaseLink = "http://www.roblox.com/catalog/json?SortType=0&SortAggregation=3&SortCurrency=0&LegendExpanded=true&Category=0&PageNumber="
        $.get(BaseLink + PageOn,function(Data){
                for (var Object in Data){
                        function Loop(){
                                var Id = Data[Object]["AssetId"]
                                $.get("http://www.roblox.com/Item-item?id=" + Id).always(function(Data){
                                        var Token = Data.match(/setToken(\W(.+)\W);/)[2].replace(/'/g,"")
                                        Token = Token.replace("+","%2B").replace("/","%2F")
                                        $.post("http://www.roblox.com/API/Comments.ashx?rqtype=makeComment&assetID=" + Id + "&token=" + Token,Comment)
                                })
                        }
                        Loop()
                }
        }).always(function(){
                PageOn++
                if (PageOn == PageLimit){
                        PageOn = 0
                }
                Go()
        })
}
Go()





---------------------------------





Troll Trade Bot:



function POST(){
        $.ajax({
                url : "/My/Money.aspx/GetMyItemTrades",
                type : "POST",
                data : '{"statustype":"inbound","startindex":0}',
                contentType : "application/json; charset=UTF-8",
                success : function(Data){
                        var Base = jQuery.parseJSON(jQuery.parseJSON(Data.d).Data[0])
                        var TradeId = Base["TradeSessionID"]
                        var Partner = Base["TradePartnerID"]
                        var Token = ""
                        $.get("/My/Money.aspx",function(Data){
                                Token = Data.match(/setToken(\W(.+)\W);/)[2].replace(/'/g,"")
                    Token = Token.replace("+","%2B").replace("/","%2F")
                        }).always(function(){
                                $.post("/Trade/TradeHandler.ashx?token=" + Token,{TradeID : TradeId,cmd : "pull"},function(Data){
                                        var Base = jQuery.parseJSON(Data.data).AgentOfferList
                                        var Other = 1
                                        if (Number(Base[0].AgentID) == Number(Partner)){
                                                Other = 0
                                        }
                                        var OtherVal = Base[Other]["OfferValue"]
                                        var SelfVal = Base[Other == 0 && 1 || 0]["OfferValue"]
                                        var TradeJSON = Data.data
                                        if (OtherVal/3 > SelfVal){
                                                $.post("/Trade/TradeHandler.ashx?token=" + Token,{TradeID : TradeId,TradeJSON:TradeJSON,cmd : "maketrade"})
                                        }
                                        POST()
                                })
                        })
                }
        })
}
POST()



---------------------------------


Message Spam Bot:




var ID = 5981119
function PM(){
        $.post("http://www.roblox.com/messages/send",{
                subject : "UMAD!",
                body : "UMAD!",
                recipientid : ID,
                cacheBuster : new Date().getTime()
        }).always(function(){
                PM()
        })
}
PM()
