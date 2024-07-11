import React, { useState } from 'react';
import axios from 'axios';

function SendEmails({token}) {
  const [emails, setEmails] = useState([]);
  const [newEmail, setNewEmail] = useState('');

  const handleEmailChange = (event) => {
    setNewEmail(event.target.value);
  };

  const handleAddEmail = () => {
    setEmails([...emails, newEmail]);
    setNewEmail('');
  };

  const handleSendEmails = async () => {
    if (emails.length === 0) {
      alert('Please enter at least one email address');
      return;
    }

    try {
        axios.defaults.headers.common['Authorization'] = `Bearer=${token}`;
        const response = await axios.post('http://127.0.0.1:9090/mail/sendToVendors', { emails });
        console.log('Emails sent successfully:', response.data);
        setEmails([]);
    } catch (error) {
      console.error('Error sending emails:', error);
    }
  };

  const removeEmail = (index) => {
    const updatedEmails = [...emails];
    updatedEmails.splice(index, 1);
    setEmails(updatedEmails);
  };

  return (
    <div>
      <h2>Send Emails to Vendors</h2>
      <input type="email" value={newEmail} onChange={handleEmailChange} placeholder="Enter Vendor Email" />
      <button onClick={handleAddEmail}>Add Email</button>
      <ul>
        {emails.map((email, index) => (
          <li key={index}>
            {email}
            <button onClick={() => removeEmail(index)}>Remove</button>
          </li>
        ))}
      </ul>
      {emails.length > 0 && <button onClick={handleSendEmails}>Send Emails</button>}
    </div>
  );
}

export default SendEmails;