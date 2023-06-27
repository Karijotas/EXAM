import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Button, FormControl, InputLabel, MenuItem, Select } from '@mui/material';
import { useState } from 'react';
import MenuBar from './MenuBar';
import { useEffect } from 'react';

const JSON_HEADERS = {
    "Content-Type": "application/json",
  };
export default function CreateMechanic() {

    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [speciality, setSpeciality] = useState("");
    const [city, setCity] = useState("");
    const [carShop, setCarShop] = useState("");
    

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

    const [carShops, setCarShops] = useState([]);

    const fetchCarShops = () => {
        fetch('/api/v1/carshop')
            .then((response) => response.json())
            .then((jsonResponse) => setCarShops(jsonResponse));
    };
    useEffect(() => {
        fetchCarShops();
    }, []
    );
    return (
        <div>
            <MenuBar />
            PRIVALOMI VISI LAUKAI
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
                <FormControl fullWidth>
  <InputLabel id="servisas">Servisai</InputLabel>
  <Select
    labelId="servisas"
    id="simple-select"
    // value={age}
    label="Servisai"
    // onChange={handleChange}
  >
    <MenuItem value={1}>Servisas1</MenuItem>
    <MenuItem value={2}>Servisas2</MenuItem>
    <MenuItem value={3}>Servisas3</MenuItem>
  </Select>
</FormControl>
            </Box><Button type='submit'
                onClick={create}
                variant='contained'>Sukurti</Button></div>
    );
}