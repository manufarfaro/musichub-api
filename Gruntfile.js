module.exports = function(grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        sass: {
            dist: {
                options: {
                    style: 'expanded',
                    trace: true
                },
                files: {
                    'grails-app/assets/stylesheets/main.css': 'grails-app/assets/stylesheets/sass/main.scss'
                }
            }
        },
        copy: {
            main: {
              files: [
                {expand: true, flatten: true, src: ['grails-app/assets/components/font-awesome-sass/assets/fonts/font-awesome/*'], dest: 'grails-app/assets/fonts/font-awesome/', filter: 'isFile'},
                {expand: true, flatten: true, src: ['grails-app/assets/components/bootstrap-sass/assets/fonts/bootstrap/*'], dest: 'grails-app/assets/fonts/', filter: 'isFile'}
              ]
            }
        },
        watch: {
            grunt: { 
            	files: ['Gruntfile.js'],
        		tasks: ['default']
            },

            sass: {
                files: ['grails-app/assets/stylesheets/sass/**/*.scss'],
                tasks: ['sass']
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-sass');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-copy');

    grunt.registerTask('default', ['copy', 'sass', 'watch']);
}