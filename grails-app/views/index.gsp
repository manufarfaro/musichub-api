<g:applyLayout name="main">
    <head>
        <title>REST API MusicHub</title>
    </head>
    <body>
    	<section class="row">
  			<div class="col-md-12 mh-body-title text-center">
  				<h1>POTENCIÁ TUS APLICACIONES CON MUSICHUB<i class="fa fa-copyright"></i></h1>
  			</div>
  		</section>
    	<div class="container">
	    	<section id="mh-info-app" class="row">
	    		<div class="col-md-12">
	    			<p>A continuación te mostramos la documentación disponible para poder integrar tu aplicación con MusicHub<i class="fa fa-copyright"></i>:</p>
	    			<div class="row mh-api-info-container">
	    				<div class="col-md-12 center-block">
			    			<div class="row">
			    				<div class="col-md-4 text-center">
			    					<a class="btn btn-info btn-block" href="https://trello.com/b/BeWIfjj5/musichub">
				    					<i class="fa fa-trello mh-api-options"></i>
				    					<h3>Pizarra Trello<i class="fa fa-copyright"></i><br/> de Desarrollo</h3>
			    					</a>
			    				</div>
			    				<div class="col-md-4 text-center">
			    					<a class="btn btn-danger btn-block" href="https://github.com/manufarfaro/musichub-api">
				    					<i class="fa fa-github mh-api-options"></i>
				    					<h3>Repositorio GitHub<i class="fa fa-copyright"></i><br/> Api REST</h3>
			    					</a>
			    				</div>
			    				<div class="col-md-4 text-center">
			    					<a class="btn btn-success btn-block" href="https://github.com/manufarfaro/musichub-api">
				    					<i class="fa fa-github mh-api-options"></i>
				    					<h3>Repositorio GitHub<i class="fa fa-copyright"></i><br/> App Frontend</h3>
			    					</a>
			    				</div>
			    			</div>
	    				</div>
	    			</div>
	    		</div>
	    	</section>
	    	<g:if env="development">
		    	<section id="mh-info-enviroment" class="row mh-info-container">
		    		<div class="col-md-12">
			    		<h1>Información de Ambiente:</h1>
			    		<div class="row">
				    		<div class="col-md-3">
				    			<h3>Application Status</h3>
					            <ul>
					                <li>Environment: ${grails.util.Environment.current.name}</li>
					                <li>App profile: ${grailsApplication.config.grails?.profile}</li>
					                <li>App version: <g:meta name="info.app.version"/></li>
					                <li>Grails version: <g:meta name="info.app.grailsVersion"/></li>
					                <li>Groovy version: ${GroovySystem.getVersion()}</li>
					                <li>JVM version: ${System.getProperty('java.version')}</li>
					                <li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
					            </ul>
				    		</div>
							<div class="col-md-2">
								<h3>Artefacts</h3>
					            <ul>
					                <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
					                <li>Domains: ${grailsApplication.domainClasses.size()}</li>
					                <li>Services: ${grailsApplication.serviceClasses.size()}</li>
					                <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>                    
					            </ul>
				    		</div>
				    		<div class="col-md-7">
				    			<h3>Installed Plugins(${applicationContext.getBean('pluginManager').allPlugins.size()})</h3>
				    			<div class="row">
				    				<g:each var="pluginItem" in="${applicationContext.getBean('pluginManager').allPlugins.toList().collate(8)}">
				    					<div class="col-md-4">
								            <ul>
								                <g:each var="plugin" in="${pluginItem}">
								                    <li>${plugin.name} - ${plugin.version}</li>
								                </g:each>
								            </ul>
				    					</div>
				    				</g:each>
				    				
				    			</div>
				    		</div>
			    		</div>
		    		</div>
		    	</section>	
		    	<section class="mh-info-container row">
		    		<div class="col-md-12">
			    		<h1>Controladores Registrados(${grailsApplication.controllerClasses.size()}):</h1>
			    		<ul>
				    		<g:if test="${grailsApplication.controllerClasses.size() < 1}">
				    			<li>
				    				<h3><i class="fa fa-remove"></i> No hay controladores disponibles</h3>
				    			</li>
				    		</g:if>
		                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
		                        <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
		                    </g:each>
		                </ul>
		    		</div>
		    	</section>
	    	</g:if>
    	<br/><br/>
    	</div>
    </body>
</g:applyLayout>
