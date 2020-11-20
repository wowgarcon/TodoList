let todoInput = document.getElementById('todoInput');
let addBtn = document.getElementById('addBtn');
let todoContainer = document.getElementById('todoContainer');
let removedEvent = false;

//=========================== 메소드 ===============================

const getTodos = () => {
    $.ajax({
        url: '/todos',
        type: 'GET'
    })
    .then((data) => {
        console.log('loadTodos SUCCESS');
        console.log(data);
        
        if(data){
            for(var i=0; i<data.length; i++){
                showTodos(data[i]);
            }
        }
    })
    .catch((error) => {
        console.log('loadTodos FAIL');
    	console.log(error);
    })
}

const showTodos = (data) =>{
    let div = document.createElement('div');
    let todoSpan = document.createElement('span');
    let iconSpan = document.createElement('span');
    let content = document.createTextNode(data.todoContent);
    let editIcon = document.createElement('i');
    let deleteIcon = document.createElement('i');
    
    div.id = `todoContentBox-${data.id}`;
    todoSpan.id = `todoSpan-${data.id}`;
    editIcon.setAttribute('onclick', `editTodo(${data.id})`);
    deleteIcon.setAttribute('onclick', `deleteTodo(${data.id})`);
    
    div.classList.add('todoContentBox');
    todoSpan.classList.add('link', 'todoSpan');
    iconSpan.classList.add('iconWrapper');
    editIcon.classList.add('fas', 'fa-edit', 'link', 'icon', 'edit');
    deleteIcon.classList.add('fas', 'fa-trash-alt', 'link', 'icon', 'delete');
    
    todoSpan.appendChild(content);
    iconSpan.appendChild(editIcon)
    iconSpan.appendChild(deleteIcon);
    div.appendChild(todoSpan);
    div.appendChild(iconSpan);
    todoContainer.appendChild(div);
}

const addTodo = () => {
    let obj = {
    	userId : 'wow',
        todoContent : todoInput.value
    }

    $.ajax({
        url: '/todo',
        type: 'POST',
        data: obj
    })
    .then((data) => {
        console.log('addTodo SUCCESS');
        console.log(data);
        showTodos(data);
    })
    .catch((error) => {
        alert("addTodo FAIL");
    	console.log(error);
    })

}

const editTodo = (id) => {

    let originalTodo = document.getElementById(`todoSpan-${id}`);
    let inputTodo = document.createElement('input');
    inputTodo.value = originalTodo.innerText;
    inputTodo.classList.add('editTodoInput');

    inputTodo.addEventListener('keydown', (e) => { 
        if(e.key == 'Enter') { 
            removedEvent = true;
            editText(e, id); 
        } 
    });

    inputTodo.addEventListener('blur', (e) => { 
        if(removedEvent){
            removedEvent = false; 
            return; 
        }
        editText(e, id) 
    });
    
    originalTodo.parentNode.replaceChild(inputTodo, originalTodo);

}

const editText = (e, id) => {
    let obj = {
        id : id,
        userId : 'wow',
        todoContent : e.target.value
    }

    $.ajax({
        url: '/todo',
        type: 'PUT',
        data: obj
    })
    .then((data) => {
        console.log('editTodo SUCCESS');
        
        let parent = e.target.parentNode;
        let todoSpan = document.createElement('span');
        todoSpan.id = `todoSpan-${data.id}`;
        todoSpan.innerText = data.todoContent;
        parent.replaceChild(todoSpan, e.target);
    })
    .catch((error) => {
        alert("editTodo FAIL");
    	console.log(error);
    })
}


const deleteTodo = (id) => {
    $.ajax({
        url: '/todo/' + id,
        type: 'DELETE'
    })
    .then(() => {
        console.log('deleteTodo SUCCESS');
        document.getElementById(`todoContentBox-${id}`).remove();
    })
    .catch((error) => {
        alert("deleteTodo FAIL");
    	console.log(error);
    })
}


//=========================== 실행부 ===============================

getTodos();

//=========================== 이벤트 ===============================

todoInput.addEventListener('keydown', (e) => {
    if(e.key == 'Enter'){
        addTodo();    
    }
})

addBtn.addEventListener('click', () => {
    addTodo();    
})
