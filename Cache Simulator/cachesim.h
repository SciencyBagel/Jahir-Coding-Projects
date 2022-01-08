/*
 * CS3375 Computer Architecture
 * Course Project
 * Cache Simulator Design and Development
 * FALL 2017
 * By Yong Chen
 * Modified by Jahir F. Montes
 */

 //===========================================
//NUM_BLOCKS = 2048

#define BLOCK_SIZE 16        /* Cache block size (or cache line size) in bytes \\
                                 (must be power of 2). 4 Bytes = 1 Word */
#define CACHE_SIZE 32768     /* Cache capacity in bytes (must be power of 2)*/
#define NUM_BLOCKS (CACHE_SIZE / BLOCK_SIZE) //512 if all values are the same
int WAY_SIZE = 1;         /* Associativity; 1-way = direct-mapped. Changed by arguments */
int NUM_SETS = 0;
//===========================================

//#define DBG
#define _CRT_SECURE_NO_WARNINGS

/*The data structure of direct-mapped cache*/
struct direct_mapped_cache {
    unsigned valid_field[NUM_BLOCKS];   /* Valid field */
    unsigned dirty_field[NUM_BLOCKS];   /* Dirty field; since we don't distinguish writes and \\
                                           reads in this project yet, this field doesn't really matter */
    uint64_t tag_field[NUM_BLOCKS];     /* Tag field */
    char data_field[NUM_BLOCKS][BLOCK_SIZE];  /* Data field; since we don't really fetch data, \\
                                                 this field doesn't really matter */
    int hits;                          /* Hit count */
    int misses;                        /* Miss count */
};

struct n_way_associative_cache {
    unsigned valid_field[NUM_BLOCKS];   /* Valid field */
    unsigned dirty_field[NUM_BLOCKS];   /* Dirty field; since we don't distinguish writes and \\
                                           reads in this project yet, this field doesn't really matter */
    uint64_t tag_field[NUM_BLOCKS];     /* Tag field */
    uint64_t index_field[NUM_BLOCKS];   /* index field used to identify set */
    char data_field[NUM_BLOCKS][BLOCK_SIZE];  /* Data field; since we don't really fetch data, \\
                                                 this field doesn't really matter */
    int hits;                          /* Hit count */
    int misses;                        /* Miss count */
};

/*Read the memory traces and convert it to binary*/
uint64_t convert_address(char memory[]);

/*Simulate the direct-mapped cache*/
void direct_mapped_cache_access(struct direct_mapped_cache *cache, uint64_t address);

void n_way_associative_cache_access(struct n_way_associative_cache *cache, uint64_t address, int n);
