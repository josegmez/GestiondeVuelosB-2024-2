--
-- PostgreSQL database dump
--

-- Dumped from database version 15.9
-- Dumped by pg_dump version 15.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: airplane_type; Type: TABLE; Schema: public; Owner: user_deploy
--

CREATE TABLE public.airplane_type (
    id character varying(255) NOT NULL,
    max_seats integer NOT NULL,
    seats_distribution character varying(255),
    type_id bigint
);


ALTER TABLE public.airplane_type OWNER TO user_deploy;

--
-- Name: city; Type: TABLE; Schema: public; Owner: user_deploy
--

CREATE TABLE public.city (
    iata_code character varying(255) NOT NULL,
    airport_name character varying(255),
    country character varying(255)
);


ALTER TABLE public.city OWNER TO user_deploy;

--
-- Name: flight; Type: TABLE; Schema: public; Owner: user_deploy
--

CREATE TABLE public.flight (
    id bigint NOT NULL,
    arrival_date date,
    arrival_time time(6) without time zone,
    departure_date date,
    departure_time time(6) without time zone,
    flight_number character varying(255) NOT NULL,
    price double precision NOT NULL,
    surcharge_percentage double precision NOT NULL,
    tax_percentage double precision NOT NULL,
    airplane_type_id character varying(255),
    destination_iata character varying(255),
    flight_type_id bigint,
    origin_iata character varying(255),
    status_id bigint
);


ALTER TABLE public.flight OWNER TO user_deploy;

--
-- Name: flight_id_seq; Type: SEQUENCE; Schema: public; Owner: user_deploy
--

CREATE SEQUENCE public.flight_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.flight_id_seq OWNER TO user_deploy;

--
-- Name: flight_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: user_deploy
--

ALTER SEQUENCE public.flight_id_seq OWNED BY public.flight.id;


--
-- Name: flight_type; Type: TABLE; Schema: public; Owner: user_deploy
--

CREATE TABLE public.flight_type (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.flight_type OWNER TO user_deploy;

--
-- Name: flight_type_id_seq; Type: SEQUENCE; Schema: public; Owner: user_deploy
--

CREATE SEQUENCE public.flight_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.flight_type_id_seq OWNER TO user_deploy;

--
-- Name: flight_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: user_deploy
--

ALTER SEQUENCE public.flight_type_id_seq OWNED BY public.flight_type.id;


--
-- Name: status; Type: TABLE; Schema: public; Owner: user_deploy
--

CREATE TABLE public.status (
    id bigint NOT NULL,
    status_name character varying(255)
);


ALTER TABLE public.status OWNER TO user_deploy;

--
-- Name: status_id_seq; Type: SEQUENCE; Schema: public; Owner: user_deploy
--

CREATE SEQUENCE public.status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.status_id_seq OWNER TO user_deploy;

--
-- Name: status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: user_deploy
--

ALTER SEQUENCE public.status_id_seq OWNED BY public.status.id;


--
-- Name: type; Type: TABLE; Schema: public; Owner: user_deploy
--

CREATE TABLE public.type (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.type OWNER TO user_deploy;

--
-- Name: type_id_seq; Type: SEQUENCE; Schema: public; Owner: user_deploy
--

CREATE SEQUENCE public.type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.type_id_seq OWNER TO user_deploy;

--
-- Name: type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: user_deploy
--

ALTER SEQUENCE public.type_id_seq OWNED BY public.type.id;


--
-- Name: flight id; Type: DEFAULT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.flight ALTER COLUMN id SET DEFAULT nextval('public.flight_id_seq'::regclass);


--
-- Name: flight_type id; Type: DEFAULT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.flight_type ALTER COLUMN id SET DEFAULT nextval('public.flight_type_id_seq'::regclass);


--
-- Name: status id; Type: DEFAULT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.status ALTER COLUMN id SET DEFAULT nextval('public.status_id_seq'::regclass);


--
-- Name: type id; Type: DEFAULT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.type ALTER COLUMN id SET DEFAULT nextval('public.type_id_seq'::regclass);


--
-- Data for Name: airplane_type; Type: TABLE DATA; Schema: public; Owner: user_deploy
--

COPY public.airplane_type (id, max_seats, seats_distribution, type_id) FROM stdin;
MAX11	23	1	2
A330	300	2-3-2	2
MAX08	248	2-2-2	1
MAX07	320	2-3-2	2
MAX09	200	2-2-2	1
A490	450	1-2-1	1
A360	20	3-1-1	1
A55	2	1	1
A44	2	1	1
A320	210	2-3-2	1
A340	210	2-3-2	1
A000	3124	1-1-1	1
\.


--
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: user_deploy
--

COPY public.city (iata_code, airport_name, country) FROM stdin;
ADZ	Aeropuerto Internacional Gustavo Rojas Pinilla	Colimbia
MAD	Adolfo Suárez Madrid-Barajas	España
MDE	Aeropuerto Internacional José María Córdova	Colombia
\.


--
-- Data for Name: flight; Type: TABLE DATA; Schema: public; Owner: user_deploy
--

COPY public.flight (id, arrival_date, arrival_time, departure_date, departure_time, flight_number, price, surcharge_percentage, tax_percentage, airplane_type_id, destination_iata, flight_type_id, origin_iata, status_id) FROM stdin;
17	2024-10-22	17:00:00	2024-10-22	08:00:00	SA1234	500	10.5	15.5	A340	MAD	2	MDE	1
18	2023-10-31	17:00:00	2023-11-01	08:00:00	SA2345	500	10.5	15.5	A340	MAD	2	MDE	1
21	2024-11-21	11:15:00	2024-11-20	11:15:00	SA5681	120	10	10	MAX09	MDE	2	MAD	1
22	2024-11-20	00:15:00	2024-11-20	11:15:00	SA2323	60	10	10	MAX09	MDE	1	ADZ	1
23	2024-11-19	01:25:00	2024-11-19	00:23:00	SA3492	80	3	3	A340	ADZ	1	MDE	1
24	2024-11-26	01:25:00	2024-11-26	00:30:00	SA3355	80	3	3	A340	ADZ	1	MDE	1
25	2024-11-26	11:26:00	2024-11-25	11:25:00	SA9923	320	12	10	MAX07	ADZ	2	MAD	1
26	2024-11-26	11:26:00	2024-11-25	11:25:00	SA9924	320	12	10	MAX07	ADZ	2	MAD	1
28	2024-11-26	11:26:00	2024-11-25	11:25:00	SA9926	320	12	10	MAX07	ADZ	2	MAD	1
29	2024-11-26	11:26:00	2024-11-25	11:25:00	SA9927	320	12	10	MAX07	ADZ	2	MAD	1
30	2024-11-26	11:26:00	2024-11-25	11:25:00	SA9929	320	12	10	MAX07	ADZ	2	MAD	1
31	2024-11-26	11:26:00	2024-11-25	11:25:00	SA9930	320	12	10	MAX07	ADZ	2	MAD	1
16	2024-10-16	03:06:00	2024-10-15	15:06:00	SA1011	300.4	0.9	19.3	A330	MAD	2	MDE	1
32	2024-11-20	11:45:00	2024-11-19	11:45:00	SA8344	13.4	34.5	425.5	MAX09	MAD	2	MDE	1
\.


--
-- Data for Name: flight_type; Type: TABLE DATA; Schema: public; Owner: user_deploy
--

COPY public.flight_type (id, name) FROM stdin;
1	Nacional
2	Internacional
\.


--
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: user_deploy
--

COPY public.status (id, status_name) FROM stdin;
1	Pendiente
2	En Curso
3	Finalizado
\.


--
-- Data for Name: type; Type: TABLE DATA; Schema: public; Owner: user_deploy
--

COPY public.type (id, name) FROM stdin;
1	Airbus
2	Boeing
\.


--
-- Name: flight_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user_deploy
--

SELECT pg_catalog.setval('public.flight_id_seq', 32, true);


--
-- Name: flight_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user_deploy
--

SELECT pg_catalog.setval('public.flight_type_id_seq', 1, false);


--
-- Name: status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user_deploy
--

SELECT pg_catalog.setval('public.status_id_seq', 1, false);


--
-- Name: type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user_deploy
--

SELECT pg_catalog.setval('public.type_id_seq', 1, false);


--
-- Name: airplane_type airplane_type_pkey; Type: CONSTRAINT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.airplane_type
    ADD CONSTRAINT airplane_type_pkey PRIMARY KEY (id);


--
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (iata_code);


--
-- Name: flight flight_pkey; Type: CONSTRAINT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.flight
    ADD CONSTRAINT flight_pkey PRIMARY KEY (id);


--
-- Name: flight_type flight_type_pkey; Type: CONSTRAINT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.flight_type
    ADD CONSTRAINT flight_type_pkey PRIMARY KEY (id);


--
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- Name: type type_pkey; Type: CONSTRAINT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.type
    ADD CONSTRAINT type_pkey PRIMARY KEY (id);


--
-- Name: flight uk_aucisqx7arn3fi6eyjmsvqdb3; Type: CONSTRAINT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.flight
    ADD CONSTRAINT uk_aucisqx7arn3fi6eyjmsvqdb3 UNIQUE (flight_number);


--
-- Name: flight fkbsi81mqdm31rn5t4qj625ov7t; Type: FK CONSTRAINT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.flight
    ADD CONSTRAINT fkbsi81mqdm31rn5t4qj625ov7t FOREIGN KEY (flight_type_id) REFERENCES public.flight_type(id);


--
-- Name: flight fkeh29fd93ivulub8c8uxl017lm; Type: FK CONSTRAINT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.flight
    ADD CONSTRAINT fkeh29fd93ivulub8c8uxl017lm FOREIGN KEY (origin_iata) REFERENCES public.city(iata_code);


--
-- Name: flight fkeipknsxcdohtvof3s02lrf97q; Type: FK CONSTRAINT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.flight
    ADD CONSTRAINT fkeipknsxcdohtvof3s02lrf97q FOREIGN KEY (status_id) REFERENCES public.status(id);


--
-- Name: flight fkhqcj1kjhgmh7bofx207hx1p02; Type: FK CONSTRAINT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.flight
    ADD CONSTRAINT fkhqcj1kjhgmh7bofx207hx1p02 FOREIGN KEY (destination_iata) REFERENCES public.city(iata_code);


--
-- Name: airplane_type fkmddo32k54fvh5qubr3bi4dsxp; Type: FK CONSTRAINT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.airplane_type
    ADD CONSTRAINT fkmddo32k54fvh5qubr3bi4dsxp FOREIGN KEY (type_id) REFERENCES public.type(id);


--
-- Name: flight fkngrodr3bleo5b6hpnj1rjkdp7; Type: FK CONSTRAINT; Schema: public; Owner: user_deploy
--

ALTER TABLE ONLY public.flight
    ADD CONSTRAINT fkngrodr3bleo5b6hpnj1rjkdp7 FOREIGN KEY (airplane_type_id) REFERENCES public.airplane_type(id);


--
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: public; Owner: cloud_admin
--

ALTER DEFAULT PRIVILEGES FOR ROLE cloud_admin IN SCHEMA public GRANT ALL ON SEQUENCES  TO neon_superuser WITH GRANT OPTION;


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: cloud_admin
--

ALTER DEFAULT PRIVILEGES FOR ROLE cloud_admin IN SCHEMA public GRANT ALL ON TABLES  TO neon_superuser WITH GRANT OPTION;


--
-- PostgreSQL database dump complete
--

