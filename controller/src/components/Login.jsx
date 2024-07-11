import axios from "axios";
import React, { useState, useEffect } from 'react';

function Login({ onLoginSuccess }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
  
    const handleSubmit = async (event) => {
      event.preventDefault();

      try{
        const response = await axios.post('http://127.0.0.1:9090/login', { username: username, password: password });
        if(response.status == 200){
            onLoginSuccess(response.data);
        } else {
            console.log("Login failed");
        }
      } catch(error){
        console.log("Login failed");
      }
        
    };
  
    return (
      <form onSubmit={handleSubmit}>
        <label htmlFor="username">Username:</label>
        <input type="email" id="username" value={username} onChange={(e) => setUsername(e.target.value)} />
        <label htmlFor="password">Password:</label>
        <input type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        <button type="submit">Login</button>
      </form>
    );
  }
  
  export default Login;