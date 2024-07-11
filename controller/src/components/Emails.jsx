import axios from 'axios';
import React, { useState, useEffect } from 'react';

function Emails({token}) {
  const [mails, setMails] = useState([]);

  useEffect(() => {
    axios.defaults.headers.common['Authorization'] = `Bearer=${token}`;
    axios.get('http://127.0.0.1:9090/mail')
      .then(response => setMails(response.data))
      .catch(error => console.error('Error fetching employees:', error));
  }, []);

  return (
    <div>
      <h2>Email List</h2>
      <table>
        <thead>
          <tr>
            <th>Recipient</th>
            <th>Sender</th>
            <th>Subject</th>
            <th>Body</th>
            <th>Time</th>
          </tr>
        </thead>
        <tbody>
          {mails.map(mail => (
            <tr key={mail.id}>
              <td>{mail.toEmail}</td>
              <td>{mail.fromEmail}</td>
              <td>{mail.subject}</td>
              <td>{mail.body}</td>
              <td>{mail.sentTime}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Emails;