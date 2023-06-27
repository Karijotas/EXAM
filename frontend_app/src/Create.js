import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Button } from '@mui/material';
import { useState } from 'react';
import MenuBar from './MenuBar';

const JSON_HEADERS = {
    "Content-Type": "application/json",
  };
export default function FormPropsTextFields() {

    const [name, setName] = useState("");
    const [description, setDescription] = useState("");


    const create = () => {
        fetch("/api/v1/menu", {
          method: "POST",
          headers: JSON_HEADERS,
          body: JSON.stringify({
            name,
            description
          }),
        })
        // .then(applyResult);
      };
  return (
    <div>
        <MenuBar/>
    <Box
      component="form"
      sx={{
        '& .MuiTextField-root': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
        <TextField
          required
          id="outlined-required"
          label="Name"
          defaultValue="Hello World"
          onChange={(e) => setName(e.target.value)}
        />
        <TextField
        required
          id="outlined"
          label="Description"
          onChange={(e) => setDescription(e.target.value)}
        
        />
    </Box><Button type='submit' 
    onClick={create} 
    variant='contained'>Press me</Button></div>
  );
}