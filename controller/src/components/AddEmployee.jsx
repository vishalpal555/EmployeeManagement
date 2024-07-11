import React, { useState } from 'react';
import axios from 'axios';

function AddEmployee({token}) {
  const [employeeData, setEmployeeData] = useState({
    email: '',
    firstName: '',
    lastName: '',
    designation: '',
    ctc: 0,
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setEmployeeData({ ...employeeData, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {

      const response = await axios.post('http://127.0.0.1:9090/employee/add', employeeData);
      console.log('Employee added successfully:', response.data);
      setEmployeeData({ email: '', firstName: '', lastName: '', designation: '', ctc: 0 });
    } catch (error) {
      console.error('Error adding employee:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="email">Email:</label>
      <input type="email" id="email" name="email" value={employeeData.email} onChange={handleChange} required />
      <label htmlFor="firstName">First Name:</label>
      <input type="text" id="firstName" name="firstName" value={employeeData.firstName} onChange={handleChange} required />
      <label htmlFor="lastName">Last Name:</label>
      <input type="text" id="lastName" name="lastName" value={employeeData.lastName} onChange={handleChange} required />
      <label htmlFor="designation">Designation:</label>
      <input type="text" id="designation" name="designation" value={employeeData.designation} onChange={handleChange} required />
      <label htmlFor="ctc">CTC:</label>
      <input type="number" id="ctc" name="ctc" value={employeeData.ctc} onChange={handleChange} required />
      <button type="submit">Add Employee</button>
    </form>
  );
}

export default AddEmployee;