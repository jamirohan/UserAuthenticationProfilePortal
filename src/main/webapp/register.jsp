<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register | User Portal</title>

    <style>

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        body {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #667eea, #764ba2);
        }

        .register-container {
            width: 420px;
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 10px;
        }

        .subtitle {
            text-align: center;
            color: #777;
            font-size: 14px;
            margin-bottom: 30px;
        }

        .success-message {
            background: #e8f8f0;
            color: #1e8449;
            border: 1px solid #a9dfbf;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
            text-align: center;
            font-size: 14px;
            font-weight: bold;
        }

        .error-message {
            background: #fdedec;
            color: #c0392b;
            border: 1px solid #f5b7b1;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
            text-align: center;
            font-size: 14px;
            font-weight: bold;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #444;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 15px;
            outline: none;
            transition: border-color 0.3s;
        }

        input:focus {
            border-color: #667eea;
        }

        .register-button {
            width: 100%;
            padding: 13px;
            border: none;
            border-radius: 8px;
            background: #667eea;
            color: white;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s;
        }

        .register-button:hover {
            background: #5568d9;
        }

        .login-link {
            text-align: center;
            margin-top: 25px;
            font-size: 14px;
            color: #666;
        }

        .login-link a {
            color: #667eea;
            text-decoration: none;
            font-weight: bold;
        }

        .login-link a:hover {
            text-decoration: underline;
        }

    </style>
</head>

<body>

<%
    String error = request.getParameter("error");
%>

    <div class="register-container">

        <%
            if ("required".equals(error)) {
        %>

            <div class="error-message">
                Please fill in all required fields.
            </div>

        <%
            }
        %>


        <%
            if ("failed".equals(error)) {
        %>

            <div class="error-message">
                Registration failed. Please try again.
            </div>

        <%
            }
        %>


        <h1>Create Account</h1>

        <p class="subtitle">
            Register for your user portal
        </p>

        <form action="RegisterServlet" method="post">

            <div class="form-group">

                <label for="fullName">
                    Full Name
                </label>

                <input type="text"
                       id="fullName"
                       name="fullName"
                       placeholder="Enter your full name"
                       required>

            </div>


            <div class="form-group">

                <label for="email">
                    Email Address
                </label>

                <input type="email"
                       id="email"
                       name="email"
                       placeholder="Enter your email"
                       required>

            </div>


            <div class="form-group">

                <label for="password">
                    Password
                </label>

                <input type="password"
                       id="password"
                       name="password"
                       placeholder="Create a password"
                       required>

            </div>


            <button type="submit"
                    class="register-button">

                Create Account

            </button>

        </form>


        <div class="login-link">

            Already have an account?

            <a href="login.jsp">
                Login
            </a>

        </div>

    </div>

</body>
</html>