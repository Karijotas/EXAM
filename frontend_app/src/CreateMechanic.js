import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Button } from '@mui/material';
import { useState } from 'react';
import MenuBar from './MenuBar';

const JSON_HEADERS = {
    "Content-Type": "application/json",
  };
export default function CreateMechanic() {

    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [speciality, setSpeciality] = useState("");
    const [city, setCity] = useState("");
    

    const create = () => {
        fetch("/api/v1/technic", {
            method: "POST",
            headers: JSON_HEADERS,
            body: JSON.stringify({
                name,
                surname,
                speciality,
                city,
            }),
        })
        // .then(applyResult);
    };
    return (
        <div>
            <MenuBar />
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
                    label="Vardas"
                    onChange={(e) => setName(e.target.value)}
                />
                <TextField
                    required
                    id="outlined"
                    label="Pavardė"
                    onChange={(e) => setSurname(e.target.value)}

                />
                <TextField
                    required
                    id="outlined"
                    label="Specialybė"
                    onChange={(e) => setSpeciality(e.target.value)}

                />
                <TextField
                    required
                    id="outlined"
                    label="Miestas"
                    onChange={(e) => setCity(e.target.value)}

                />
            </Box><Button type='submit'
                onClick={create}
                variant='contained'>Sukurti</Button></div>
    );
}