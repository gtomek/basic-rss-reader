basic-rss-reader
================

Android implementation of a basic RSS reader. Please feel free to reuse/improve it.
This application was developed during one busy Saturday, so do not expect it to be perfect, however it demonstrates how to use Android AsyncTask, SAX Parser and how to open new WebView. Maybe it will be useful for someone.

Known issues
============

Encoding problems
-----------------
In some cases the used SAX XML parser will be unable to properly decode some characters. 
This is described here (very similar situation):
http://stackoverflow.com/questions/9880158/sax-parser-encoding-in-java
As a remedy ROME parser could be used in the future:
https://rometools.jira.com/wiki/display/ROME/Home

Running on UI thread
--------------------
Some elements of the application still run on the UI thread. It will be improved in the future and tested in **strict** mode. E.g. the moment when the list of retrieved feeds is inserted into a listView can potentially take some time and could be optimised.

No configuration
----------------
The RSS feed is fetched now from Shazam website directly. A configuration option should be added to the app allowing modification of this parameters.