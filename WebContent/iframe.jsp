<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Iframe5</title>
        <script type="text/javascript" src="javascripts/jquery.js"></script>
        <script type="text/javascript" src="javascripts/jquery-ui.js"></script>
        <!-- <link href="js/cssjq.css" rel="stylesheet" type="text/css"> -->
        <link rel="stylesheet" href="css_styles/cssjq.css" />
        <style>
            html {
                font-size:10px;
            }
            .iframetab {
                width:100%;
                height:auto;
                border:30px;
                margin:0px;
                background:url("images/iframeno.png");
                position:relative;
                top:-13px;
            }
            .ui-tabs-panel {
                padding:5px !important;
            }
            .openout {
                float:right;
                position:relative;
                top:-28px;
                left:-5px;
				
            }
        </style>
        <script>
            $(document).ready(function() {
                var $tabs = $('#tabs').tabs();
                //get selected tab
                function getSelectedTabIndex() {
                    return $tabs.tabs('option', 'active');
                }
                //get tab contents
                beginTab = $("#tabs ul li:eq(" + getSelectedTabIndex() + ")").find("a");
                loadTabFrame($(beginTab).attr("href"),$(beginTab).attr("rel"));
                $("a.tabref").click(function() {
                    loadTabFrame($(this).attr("href"),$(this).attr("rel"));
                });
                //tab switching function
                function loadTabFrame(tab, url) {
                    if ($(tab).find("iframe").length == 0) {
                        var html = [];
                        html.push('<div class="tabIframeWrapper">');
                        html.push('<div class="openout"><a href="' + url + '"><img src="images/world_thumb.png" border="0" alt="Open" title="Remove iFrame" /></a></div><iframe class="iframetab" src="' + url + '">Load Failed?</iframe>');
                        html.push('</div>');
                        $(tab).append(html.join(""));
                        $(tab).find("iframe").height($(window).height()-80);
                    }
                    return false;
                }
            });
        </script>
</head>
<body>
	  <div id="tabs">
            <ul>
                <li><a class="tabref" href="#tabs-1" rel="https://www.google.com/">Google</a></li>
                <li><a class="tabref" href="#tabs-2" rel="https://www.yahoo.com/">Yahoo</a></li>
                <li><a class="tabref" href="#tabs-3" rel="http://bing.com">Bing</a></li>
            </ul>
            <div id="tabs-1" class="tabMain">
            </div>
            <div id="tabs-2">
            </div>
            <div id="tabs-3">
            </div>
        </div> 
</body>
</html>