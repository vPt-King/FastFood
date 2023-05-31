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

app.get('/admin/invoice', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/invoice/invoice.html");
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

app.get('/admin/order', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/order/order.html");
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

app.get('/admin/customer-of-user', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/customer/customerOfUser.html");
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

app.get('/admin/view-order', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/order/viewOrder.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/carousel', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "/admin/carousel.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/settings', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "/admin/settings.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/user-queries', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "/admin/user_queries.html");
    }
    else{
        res.redirect("/admin/login");
    }
});

app.get('/admin/adminLogout', function(req, res) {
    res.redirect("/admin/login");
});

app.get('/admin', function(req, res) {
    if(req.cookies.isAdmin){
    res.sendFile(views + "admin/homepage.html");
    }
    else{
        res.redirect("/admin/login");
    }
});


//user routes



app.get('/login', function(req, res) {
    res.sendFile(views + "user/login.html");
});

app.get('/register', function(req, res) {
    res.sendFile(views + "user/register.html");
});

app.get('/forgot-password', function(req, res) {
    res.sendFile(views + "user/forgotPassword.html");
});

app.get('/contact', function(req, res) {
    res.sendFile(views + "user/contact.html");
});

app.get('/products_category', function(req, res) {
    res.sendFile(views + "user/products_category.html");
});


app.get('/product_detail', function(req, res) {
    res.sendFile(views + "user/detail.html");
});

app.get('/cart', function(req, res) {
    res.sendFile(views + "user/cart.html");
});

app.get('/checkout', function(req, res) {
    res.sendFile(views + "user/checkout.html");
});

app.get('/orders', function(req, res) {
    res.sendFile(views + "user/history.html");
});

app.get('/viewOrder', function(req, res) {
    res.sendFile(views + "user/viewOrder.html");
});


app.get('/', function(req, res) {
    res.sendFile(views + "user/homepage.html");
});


app.listen(port, () => {
    console.log(`Server is running at http://localhost:${port}`);
});