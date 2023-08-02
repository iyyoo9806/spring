ClassicEditor
    .create( document.querySelector('#contents') )
    .then( editor => { 
        console.log(editor); 
    })
    .catch( error => { 
        console.error(error); 
    });
