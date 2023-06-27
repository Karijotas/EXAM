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
import { useParams } from 'react-router-dom';



export default function ListTechnics() {

    const [technics, setTechnics] = useState([]);

    const fetchTechnic = () => {
        fetch(`/api/v1/technic/`)
            .then((response) => response.json())
            .then((jsonResponse) => setTechnics(jsonResponse));
    };
    useEffect(() => {
        fetchTechnic();
    }, []
    );



    return (
        <div>
            <MenuBar />
            <TableContainer className='klas' component={Paper} sx={{ width: 600 }}>
                <Table aria-label="a dense table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Vardas</TableCell>
                            <TableCell> Pavardė</TableCell>
                            <TableCell> Savininkas</TableCell>
                            <TableCell>Įvertinti</TableCell>


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
                                {/* <TableCell >{carShop.adress}</TableCell>
                                <TableCell> {carShop.owner}</TableCell> */}
                                {/* <Button>List</Button> */}

                            </TableRow>))}

                    </TableBody>
                </Table>
            </TableContainer></div>
    );
}