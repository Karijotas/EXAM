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
export default function UpdateCarShop() {
    const params = useParams();

    const [name, setName] = useState("");
    const [adress, setAdress] = useState("");
    const [owner, setOwner] = useState("");

    const[carshops, setCarShops] = useState({
        name: "", 
        adress: "",
        owner: "",
    })

    useEffect(() => {
        fetch('/api/v1/carshop/' + params.id)
            .then((response) => response.json())
            .then((jsonResponse) => setCarShops(jsonResponse));
    }, []);

    const update = () => {
        fetch("/api/v1/carshop/" + params.id, {
            method: "PATCH",
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
                    label={carshops.name}
                    value={name}
                    onChange={(e) => setName(e.target.value) }

                />
                <TextField
                    required
                    id="outlined"
                    label={carshops.adress}
                    value={adress}
                    onChange={(e) => setAdress(e.target.value)}

                />
                <TextField
                    required
                    id="outlined"
                    label={carshops.owner}
                    value={owner}
                    onChange={(e) => setOwner(e.target.value)}

                />
                {console.log(name, adress, owner)}
            </Box><Button type='submit'
                onClick={update}
                variant='contained'>Atnaujinti</Button></div>
    );
}