//v.2.0 build 81009

/*
Copyright DHTMLX LTD. http://www.dhtmlx.com
To use this component please contact sales@dhtmlx.com to obtain license
*/
dhtmlXWindows.prototype._enableStatusBar = function() {this._attachStatusBar = function(win, bar, show) {var wt = (win._toolbarT!=null?win._toolbarT:0);var mt = (win._menuT!=null?win._menuT:0);win._sbId = win._sidd;win._sbH = (_isIE?19:18);win._sbB = (_isIE?20:19);win.sb = win._content.childNodes[3];win.sb.className = "dhtmlxWebStatusBarInWin_"+this.skin;win._content.childNodes[3].style.display = "";win._content.childNodes[3].style.height = win._sbH + "px";win._content.childNodes[2].style.top = wt + mt + "px";win._content.childNodes[2].style.bottom = win._sbB + "px";if (_isIE){win._IEFixMTS = true;var pad = 19 + wt + mt;win._content.childNodes[2].style.paddingBottom = pad+"px"};win.sb.setText = function(text) {this.innerHTML = text};win.sb.getText = function() {return this.innerHTML};if (win.accordion != null){win.accordion.setSizes()};if (win.layout != null){win.layout.setSizes(win)};return win.sb}};
//v.2.0 build 81009

/*
Copyright DHTMLX LTD. http://www.dhtmlx.com
To use this component please contact sales@dhtmlx.com to obtain license
*/