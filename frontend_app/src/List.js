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
import { Button, Container, Typography } from '@mui/material';



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

    const deleteCarShop = (id) => {
        fetch("/api/v1/carshop/delete/" + id, {
          method: "DELETE",
        })
        .then(fetchCarShops());
      };


    return (
        <div>
            <MenuBar />
          
            <TableContainer className='klas' component={Paper} sx={{ width: 1200 }}>
                <Table aria-label="a dense table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Servisas</TableCell>
                            <TableCell> Adresas</TableCell>
                            <TableCell> Savininkas</TableCell>
                            <TableCell> Taisyti</TableCell>
                            <TableCell> Trinti</TableCell>


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
                                <TableCell><Button href={'#/list/carshop/' + carShop.id}>Taisyti</Button></TableCell>
                                <TableCell><Button onClick={() => deleteCarShop(carShop.id)} color='error'>Trinti</Button></TableCell>

                            

                            </TableRow>))}

                </TableBody>
            </Table>
        </TableContainer>
        <Container>***</Container>
        <Typography
                        variant="h6"
                        noWrap
                        component="a"
                        href='#/create'
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
                        Pridėti naują servisą
                    </Typography></div >
    );
}