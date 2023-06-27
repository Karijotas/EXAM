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
import { Button } from '@mui/material';



export default function DenseTable() {

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
            <TableContainer className='klas' component={Paper} sx={{ width: 600 }}>
                <Table aria-label="a dense table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Servisas</TableCell>
                            <TableCell> Adresas</TableCell>
                            <TableCell> Savininkas</TableCell>


                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {carShops.map((carShop) => (
                            <TableRow
                                key={carShop.id}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                            >
                                <TableCell component="th" scope="row">
                                    {carShop.name}
                                </TableCell>
                                <TableCell >{carShop.adress}</TableCell>
                                <TableCell> {carShop.owner}</TableCell>
                            
                             {console.log(carShop.id)}

                            </TableRow>))}

                </TableBody>
            </Table>
        </TableContainer></div >
    );
}