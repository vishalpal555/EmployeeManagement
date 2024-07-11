import axios from 'axios';
import React, { useState, useEffect } from 'react';

function Employees({token}) {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    axios.defaults.headers.common['Authorization'] = `Bearer=${token}`;
    axios.get('http://127.0.0.1:9090/employee')
      .then(response => setEmployees(response.data))
      .catch(error => console.error('Error fetching employees:', error));
  }, []);

  return (
    <div>
      <h2>Employee List</h2>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Designation</th>
            <th>CTC</th>
          </tr>
        </thead>
        <tbody>
          {employees.map(employee => (
            <tr key={employee.id}>
              <td>{employee.firstName +" " +employee.lastName}</td>
              <td>{employee.email}</td>
              <td>{employee.designation}</td>
              <td>{employee.ctc}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Employees;