//v.2.0 build 81009

/*
Copyright DHTMLX LTD. http://www.dhtmlx.com
To use this component please contact sales@dhtmlx.com to obtain license
*/
dhtmlXWindows.prototype._enableWebToolbar = function() {this._attachWebToolbar = function(win) {win._toolbarId = win._tidd;win._toolbarH = (_isIE?24:24)
 win._toolbarT = (_isIE?23:24);win.toolbar = new dhtmlXToolbarObject(win._toolbarId, this.skin);win._content.childNodes[1].style.display = "";win._content.childNodes[1].style.height = win._toolbarH + "px";win._content.childNodes[2].style.top = win._toolbarT + (win._menuT!=null?win._menuT:0) + "px";if (_isIE){win._IEFixMTS = true;var pad = win._toolbarT + (win._menuT!=null?win._menuT:0) + (win._sbH!=null?win._sbH:0);if (document.compatMode != "BackCompat"){win._content.childNodes[2].style.marginBottom = pad + "px"}else {win._content.childNodes[2].style.paddingBottom = pad + "px"}};if (win.menu != null){win._content.childNodes[1].className += " dhtmlxToolbar_"+this.skin+"_bottom_top"};if (win.grid != null){win.grid.setSizes()};if (win.accordion != null){win.accordion.setSizes()};if (win.layout != null){win.layout.setSizes(win)};if (_isIE=="adv"){var that = this;var dim = win.getDimension();win.setDimension(dim[0], dim[1]+1);that._redrawWindow(win);window.setTimeout(function(){var dim=win.getDimension();win.setDimension(dim[0],dim[1]-1);that._redrawWindow(win)},1)};return win.toolbar}};
//v.2.0 build 81009

/*
Copyright DHTMLX LTD. http://www.dhtmlx.com
To use this component please contact sales@dhtmlx.com to obtain license
*/