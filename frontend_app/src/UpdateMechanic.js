import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Button } from '@mui/material';
import { useState } from 'react';
import MenuBar from './MenuBar';
import { useParams } from 'react-router-dom';
import { useEffect } from 'react';

const JSON_HEADERS = {
    "Content-Type": "application/json",
};
export default function UpdateMechanic() {
    const params = useParams();


    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [speciality, setSpeciality] = useState("");
    const [city, setCity] = useState("");

    const update = () => {
        fetch("/api/v1/technic/" + params.id, {
            method: "PATCH",
            headers: JSON_HEADERS,
            body: JSON.stringify({
                name,
                surname,
                speciality,
                city,
            }),
        })
    };


    const [technics, setTechnics] = useState({
        name: "",
        surname: "",
        speciality: "",
        city: "",
    })

    useEffect(() => {
        fetch('/api/v1/technic/' + params.id)
            .then((response) => response.json())
            .then((jsonResponse) => setTechnics(jsonResponse));
    }, []);

    return (
        <div>
            <MenuBar />PRIVALOMI VISI LAUKAI
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
                    id="outlined"
                    label={technics.name}
                    value={name}
                    onChange={(e) => setName(e.target.value)}

                />
                <TextField
                    required
                    id="outlined"
                    label={technics.surname}
                    value={surname}
                    onChange={(e) => setSurname(e.target.value)}

                />
                <TextField
                    required
                    id="outlined"
                    label={technics.speciality}
                    value={speciality}
                    onChange={(e) => setSpeciality(e.target.value)}
                />

                <TextField
                    required
                    id="outlined"
                    label={technics.city}
                    value={city}
                    onChange={(e) => setCity(e.target.value)}

                />
            </Box><Button type='submit'
                onClick={update}
                variant='contained'>Atnaujinti</Button></div>
    );
}