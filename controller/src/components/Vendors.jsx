import axios from 'axios';
import React, { useState, useEffect } from 'react';

function Vendors({token}) {
  const [vendors, setVendors] = useState([]);

  useEffect(() => {
    axios.defaults.headers.common['Authorization'] = `Bearer=${token}`;
    axios.get('http://127.0.0.1:9090/vendor')
      .then(response => setVendors(response.data))
      .catch(error => console.error('Error fetching employees:', error));
  }, []);

  return (
    <div>
      <h2>Vendor List</h2>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>UPI ID</th>
          </tr>
        </thead>
        <tbody>
          {vendors.map(vendor => (
            <tr key={vendor.id}>
              <td>{vendor.name}</td>
              <td>{vendor.email}</td>
              <td>{vendor.upiId}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Vendors;