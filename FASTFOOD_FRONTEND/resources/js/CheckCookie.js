function checkCookie(cookieName) {
    var cookies = document.cookie.split(';'); // Get all cookies as an array
  
    // Loop through the cookies to find the desired one
    for (var i = 0; i < cookies.length; i++) {
      var cookie = cookies[i].trim(); // Remove leading/trailing whitespace
  
      // Check if the cookie starts with the desired name
      if (cookie.indexOf(cookieName + '=') === 0) {
        return true; // Cookie exists
      }
    }
  
    return false; // Cookie does not exist
  }