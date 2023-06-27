import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { useState, useEffect } from "react";
import './App.css';
import MenuBar from './MenuBar';
import { Button, Container, Rating, Typography } from '@mui/material';



export default function ListTechnics() {

    const [technics, setTechnics] = useState([]);
    const [review, setReview] = useState([]);
    const [active, setActive] = useState("");


    const fetchTechnic = () => {
        fetch(`/api/v1/technic/`)
            .then((response) => response.json())
            .then((jsonResponse) => setTechnics(jsonResponse));
    };
    useEffect(() => {
        fetchTechnic();
    }, []
    );

    const deleteTechnic = (id) => {
        fetch("/api/v1/technic/delete/" + id, {
          method: "DELETE",
        })
        .then(fetchTechnic());
      };
    

    // const handleReview = (e) => {
    //     fetch(`/api/v1/technic/${e}`)
    //         .then((response) => response.json())
    //         .then((jsonResponse) => setTechnics(jsonResponse));
    // };






    return (
        <div>
            <MenuBar />
            <TableContainer className='klas' component={Paper} sx={{ width: 1200 }}>
                <Table aria-label="a dense table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Vardas</TableCell>
                            <TableCell>Pavardė</TableCell>
                            <TableCell>Specialybė</TableCell>
                            <TableCell>Miestas</TableCell>
                            <TableCell>Įvertinti</TableCell>
                            <TableCell> Taisyti</TableCell>
                            <TableCell> Trinti</TableCell>

                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {technics.map((technic) => (
                            <TableRow
                                key={technic.id}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                            >
                                <TableCell component="th" scope="row">
                                    {technic.name}
                                </TableCell>
                                <TableCell>{technic.surname}</TableCell>
                                <TableCell>{technic.speciality}</TableCell>
                                <TableCell>{technic.city}</TableCell>
                                <TableCell><Rating
                                    name="simple-controlled"
                                    value={technic.review}
                                    onClick={() => { setActive(technic.id); }}
                                    onChange={(e) => { setReview(e.value) }}
                                /><Button
                                    onClick={console.log(active, review)}
                                >Pateikti</Button></TableCell>
                                <TableCell><Button>Taisyti</Button></TableCell>
                                <TableCell><Button onClick={() => deleteTechnic(technic.id)} color='error'>Trinti</Button></TableCell>

                            </TableRow>))}

                    </TableBody>
                </Table>
            </TableContainer><Container>***</Container>
            <Typography
                variant="h6"
                noWrap
                component="a"
                href='#/create/technics'
                sx={{
                    mr: 2,
                    display: { xs: 'none', md: 'flex' },
                    fontFamily: 'monospace',
                    fontWeight: 700,
                    letterSpacing: '.3rem',
                    color: 'primary',
                    textDecoration: 'none',
                }}
            >
                Pridėti naują mechaniką
            </Typography></div>
    );
}