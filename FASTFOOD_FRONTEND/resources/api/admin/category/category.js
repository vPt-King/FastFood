function get_category()
{
    let xhr = new XMLHttpRequest();
    xhr.open("GET","http://localhost:8080/admin/category",true);
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.onload = function()
    {
        CategoryList = JSON.parse(this.responseText);
        document.getElementById("category_show_body").innerHTML = "";
        CategoryList.forEach(function(category){
            htmlInner = `
            <tr>
                <th>${category.id}</th>
                <td>${category.name}</td>
                <td>
                    <a href="/admin/edit-category?id=${category.id}" class ="btn btn-default">Edit</a>
                    <a class = "btn btn-danger" data-toggle="modal" data-target="#delete-category" data-id="${category.id}">Delete</a>
                </td>
            </tr>
            `;
            document.getElementById("category_show_body").innerHTML += htmlInner;
        })
    }
    xhr.send();
}


window.onload = function(){
    get_category();
}