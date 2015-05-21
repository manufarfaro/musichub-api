<g:applyLayout name="main">
    <head>
        <title>404 - MusicHub</title>
    </head>
    <body>
    	<div class="container mh-container">
	    	<section class="row mh-alert-notfound">
	    		<div class="col-md-12">
	    			<div class="row">
	    				<div class="col-md-12">
	    					<div class="alert alert-danger" role="alert">
	    						<h3>Error: Page Not Found (404)</h3>
	    						<p>Path: ${request.forwardURI}</p>
	    					</div>
	    				</div>
	    			</div>
	    		</div>
	    	</section>
    	</div>
    </body>
</g:applyLayout>