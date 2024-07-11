import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Login from './components/Login';
import "./App.css";
import Employees from './components/Employees';
import Vendors from './components/Vendors';
import Emails from './components/Emails';
import AddEmployee from './components/AddEmployee';
import AddVendor from './components/AddVendor';
import SendEmails from './components/SendEmails';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [token, setToken] = useState(null);

  const handleLoginSuccess = (newToken) => {
    setToken(newToken);
    setIsLoggedIn(true);
  };
  const ProtectedContent = () => <h1>Welcome, you are logged in!</h1>;

  return (
    <div className='App'>
      <Router>
        <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/employees">Employees</Link>
            </li>
            <li>
              <Link to="/vendors">Vendors</Link>
            </li>
            <li>
              <Link to="/emails">Emails</Link>
            </li>
            <li>
              <Link to="/login">Login</Link>
            </li>
            <li>
              <Link to="/addEmployee">Add Employee</Link>
            </li>
            <li>
              <Link to="/addVendor">Add Vendor</Link>
            </li>
            <li>
              <Link to="/sendMails">Send Mails</Link>
            </li>
          </ul>
        </nav>

        <Routes>
          <Route path="/login" element={<Login onLoginSuccess={handleLoginSuccess} />} />
          {/* <Route path="/register" element={<Register />} /> */}
          <Route path="/" element={isLoggedIn ? 
                                                <ProtectedContent /> 
                                                : <Login onLoginSuccess={handleLoginSuccess} 
                                  />}
          />
          <Route path="/employees" element={isLoggedIn ? 
                                                <Employees token={token} />
                                                : <Login onLoginSuccess={handleLoginSuccess} 
                                  />}
          />
          <Route path="/vendors" element={isLoggedIn ? 
                                                <Vendors token={token} />
                                                : <Login onLoginSuccess={handleLoginSuccess} 
                                  />}
          />
          <Route path="/emails" element={isLoggedIn ? 
                                                <Emails token={token} />
                                                : <Login onLoginSuccess={handleLoginSuccess} 
                                  />}
          />
          <Route path="/addEmployee" element={isLoggedIn ? 
                                                <AddEmployee token={token} />
                                                : <Login onLoginSuccess={handleLoginSuccess} 
                                  />}
          />
          <Route path="/addVendor" element={isLoggedIn ? 
                                                <AddVendor token={token} />
                                                : <Login onLoginSuccess={handleLoginSuccess} 
                                  />}
          />
          <Route path="/sendMails" element={isLoggedIn ? 
                                                <SendEmails token={token} />
                                                : <Login onLoginSuccess={handleLoginSuccess} 
                                  />}
          />
        </Routes>
      </Router>
    </div>
  );
}

export default App;