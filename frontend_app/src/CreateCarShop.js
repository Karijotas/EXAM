import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Button } from '@mui/material';
import { useState } from 'react';
import MenuBar from './MenuBar';

const JSON_HEADERS = {
    "Content-Type": "application/json",
};
export default function CreateCarShop() {

    const [name, setName] = useState("");
    const [adress, setAdress] = useState("");
    const [owner, setOwner] = useState("");

    const create = () => {
        fetch("/api/v1/carshop", {
            method: "POST",
            headers: JSON_HEADERS,
            body: JSON.stringify({
                name,
                adress,
                owner,
            }),
        })
        // .then(applyResult);
    };
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
                    label="Pavadinimas"
                    onChange={(e) => setName(e.target.value)}
                />
                <TextField
                    required
                    id="outlined"
                    label="Adresas"
                    onChange={(e) => setAdress(e.target.value)}

                />
                <TextField
                    required
                    id="outlined"
                    label="Vadovas"
                    onChange={(e) => setOwner(e.target.value)}

                />
            </Box><Button type='submit'
                onClick={create}
                variant='contained'>Sukurti</Button></div>
    );
}