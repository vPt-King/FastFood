const express = require('express');
const app = express();
const cookieParser = require('cookie-parser');
const port = 3000;
var views = __dirname + "/resources/views/"
app.use(express.static(__dirname + "/resources"));
app.use(cookieParser());


//admin routes
app.get('/admin/login', function(req, res) {
    res.sendFile(views + "admin/login.html");
});

app.get('/admin/register', function(req, res) {
    res.sendFile(views + "admin/register.html");
});

app.get('/admin/category', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/category/category.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/more-category', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/category/moreCategory.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/edit-category', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/category/editCategory.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/product', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/product/product.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/more-product', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/product/moreProduct.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/edit-product', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/product/editProduct.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/product-detail', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/product/productDetail.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/user', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/user/user.html");
    }
    else{
        res.redirect("/admin/login");
    }
});


app.get('/admin/more-user', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/user/moreUser.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/edit-user', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/user/editUser.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/customer', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/customer/customer.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/more-customer', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/customer/moreCustomer.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/edit-customer', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/customer/editCustomer.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/homepage.html");
    }
    else{
        res.redirect("/admin/login");
    }
});
// app.get('/', function(req, res) {
//     res.sendFile(views + "Client/HomePage.html");
// });


app.listen(port, () => {
    console.log(`Server is running at http://localhost:${port}`);
});