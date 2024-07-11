import React, { useState } from 'react';
import axios from 'axios';

function AddVendor({token}) {
  const [vendorData, setVendorData] = useState({
    email: '',
    name: '',
    upiId: ''
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setEmployeeData({ ...employeeData, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
        axios.defaults.headers.common['Authorization'] = `Bearer=${token}`;
        const response = await axios.post('http://127.0.0.1:9090/vendor/add', employeeData);
        console.log('Employee added successfully:', response.data);
        setEmployeeData({ email: '', name: '', upiId: ''});
    } catch (error) {
      console.error('Error adding vendor:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="email">Email:</label>
      <input type="email" id="email" name="email" value={vendorData.email} onChange={handleChange} required />
      <label htmlFor="name">Name:</label>
      <input type="text" id="name" name="name" value={vendorData.name} onChange={handleChange} required />
      <label htmlFor="lastName">UPI ID:</label>
      <input type="text" id="upiId" name="upiId" value={vendorData.upiId} onChange={handleChange} required />
      <button type="submit">Add Vendor</button>
    </form>
  );
}

export default AddVendor;