<!doctype html>
<html lang="es" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title><g:layoutTitle default="REST API Musichub"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
        <asset:stylesheet src="application.css"/>
        <asset:javascript src="application.js"/>

        <g:layoutHead/>
    </head>
    <body>
    	<div class="container-fluid mh-container">
    		<nav class="navbar navbar-default navbar-fixed-top">
  				<div class="container">
  					<div class="row">
  						<div class="col-md-8 col-xs-11">
  							<div class="navbar-header">
						      <a class="navbar-brand" href="#">
						      	<img src="${assetPath(src: 'mh-logo-header-inverted.png')}" />
						      </a>
						    </div>
  						</div>
  						<div class="col-md-4 col-xs-1">
						    <ul class="nav navbar-nav navbar-right pull-right">
				        		<li>
				        			<a class="mh-links-social" href="http://github.com/manufarfaro/musichub-api" target="_blank">
						        		<i class="fa fa-github mh-links-social-icon"></i>
						        	</a>
				        		</li>
				        	</ul>
  						</div>
  					</div>
				  </div>
  			</nav>
  			<section class="row mh-content-wrapper">
  				<div class="col-md-12">
			        <g:layoutBody/>
  				</div>
  			</section>
  			<footer class="row">
  				<div class="col-md-12">
  					<p class="pull-right">MusicHub. <g:formatDate format="yyyy" date="${new Date()}"/> <i class="fa fa-copyright"></i></p>
  				</div>
  			</footer>
    	</div>
    </body>
</html>
